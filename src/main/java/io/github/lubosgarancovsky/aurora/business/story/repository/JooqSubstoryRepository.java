package io.github.lubosgarancovsky.aurora.business.story.repository;

import io.github.lubosgarancovsky.aurora.business.JooqRepository;
import io.github.lubosgarancovsky.aurora.domain.board.entity.ImmutableStoryStateEntity;
import io.github.lubosgarancovsky.aurora.domain.board.entity.StoryStateEntity;
import io.github.lubosgarancovsky.aurora.domain.story.command.CreateSubstoryCommand;
import io.github.lubosgarancovsky.aurora.domain.story.command.UpdateSubstoryCommand;
import io.github.lubosgarancovsky.aurora.domain.story.entity.ImmutableStoryTypeEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.ImmutableSubstoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryTypeEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.SubstoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.query.SubstoryDetailQuery;
import io.github.lubosgarancovsky.aurora.repository.model.tables.Partners;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.ImmutableEntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutablePublicUserResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Projects.PROJECTS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Stories.STORIES;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.StoryState.STORY_STATE;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.StoryType.STORY_TYPE;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Substories.SUBSTORIES;
import static io.github.lubosgarancovsky.aurora.rest_api.error.ProjectErrorCode.PROJECT_BY_STORY_ID_NOT_FOUND;
import static io.github.lubosgarancovsky.aurora.rest_api.error.ProjectErrorCode.PROJECT_NOT_FOUND;
import static io.github.lubosgarancovsky.aurora.rest_api.error.StoryErrorCode.STORY_NOT_FOUND;

@Repository
public class JooqSubstoryRepository extends JooqRepository {

    protected JooqSubstoryRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public EntityCreatedResponse insert(CreateSubstoryCommand command) {
        UUID projectId = this.getProjectIdByStoryId(command.parentId());
        String code = getSubstoryCode(projectId);

        final var substory = dslContext.insertInto(SUBSTORIES)
                .set(SUBSTORIES.NAME, command.name())
                .set(SUBSTORIES.DESCRIPTION, command.description())
                .set(SUBSTORIES.PARENT_ID, command.parentId())
                .set(SUBSTORIES.CODE, code)
                .set(SUBSTORIES.STATE_ID, command.stateId())
                .set(SUBSTORIES.TYPE_ID, command.typeId())
                .set(SUBSTORIES.CREATED_BY, command.userId())
                .set(SUBSTORIES.ASSIGNED_TO, command.assigneeId().orElse(null))
                .returning()
                .fetchOne();

       if(substory == null) {
           throw new RuntimeException("Failed to create substory");
       }

       return ImmutableEntityCreatedResponse.builder()
                .id(substory.get(SUBSTORIES.ID).toString())
                .build();
    }

    public SubstoryEntity update(UpdateSubstoryCommand command) {
        dslContext.update(SUBSTORIES)
                .set(SUBSTORIES.NAME, command.name())
                .set(SUBSTORIES.DESCRIPTION, command.description())
                .set(SUBSTORIES.PARENT_ID, command.storyId())
                .set(SUBSTORIES.STATE_ID, command.stateId())
                .set(SUBSTORIES.TYPE_ID, command.typeId())
                .set(SUBSTORIES.ASSIGNED_TO, command.assigneeId().orElse(null))
                .where(SUBSTORIES.ID.eq(command.substoryId()))
                .execute();

        return this.detail(command.substoryId());
    }

    public SubstoryEntity detail(SubstoryDetailQuery query) {
        return this.detail(query.substoryId());
    }

    private SubstoryEntity detail(UUID substoryId) {

        Partners createdBy = PARTNERS.as("created_by");
        Partners assignedTo = PARTNERS.as("assigned_to");

        final var record = dslContext
                .select(
                        SUBSTORIES.ID,
                        SUBSTORIES.NAME,
                        SUBSTORIES.DESCRIPTION,
                        SUBSTORIES.CODE,
                        SUBSTORIES.PARENT_ID,
                        SUBSTORIES.CREATED_AT,
                        createdBy.ID,
                        createdBy.NAME,
                        createdBy.EMAIL,
                        createdBy.PICTURE,
                        createdBy.COLOR,
                        assignedTo.ID,
                        assignedTo.NAME,
                        assignedTo.EMAIL,
                        assignedTo.PICTURE,
                        assignedTo.COLOR,
                        STORY_STATE.ID,
                        STORY_STATE.NAME,
                        STORY_STATE.CODE,
                        STORY_TYPE.ID,
                        STORY_TYPE.NAME,
                        STORY_TYPE.CODE
                )
                .from(SUBSTORIES)
                .join(STORY_STATE).on(STORY_STATE.ID.eq(SUBSTORIES.STATE_ID))
                .join(STORY_TYPE).on(STORY_TYPE.ID.eq(SUBSTORIES.TYPE_ID))
                .join(createdBy).on(createdBy.ID.eq(SUBSTORIES.CREATED_BY))
                .leftJoin(assignedTo).on(assignedTo.ID.eq(SUBSTORIES.ASSIGNED_TO))
                .where(SUBSTORIES.ID.eq(substoryId))
                .fetchOne();

        if(record == null) {
            throw STORY_NOT_FOUND.createError(substoryId.toString()).convertToException();
        }

        PublicUserResponse createdByUser = ImmutablePublicUserResponse.builder()
                .id(record.get(createdBy.ID).toString())
                .name(record.get(createdBy.NAME))
                .email(record.get(createdBy.EMAIL))
                .picture(record.get(createdBy.PICTURE))
                .color(record.get(createdBy.COLOR))
                .build();

        PublicUserResponse assignedToUser = record.get(assignedTo.ID) == null ? null : ImmutablePublicUserResponse.builder()
                .id(record.get(assignedTo.ID).toString())
                .name(record.get(assignedTo.NAME))
                .email(record.get(assignedTo.EMAIL))
                .picture(record.get(assignedTo.PICTURE))
                .color(record.get(assignedTo.COLOR))
                .build();

        StoryStateEntity state = ImmutableStoryStateEntity.builder()
                .id(record.get(STORY_STATE.ID))
                .name(record.get(STORY_STATE.NAME))
                .code(record.get(STORY_STATE.CODE))
                .build();

        StoryTypeEntity type = ImmutableStoryTypeEntity.builder()
                .id(record.get(STORY_TYPE.ID))
                .name(record.get(STORY_TYPE.NAME))
                .code(record.get(STORY_TYPE.CODE))
                .build();

        return ImmutableSubstoryEntity.builder()
                .id(record.get(SUBSTORIES.ID))
                .name(record.get(SUBSTORIES.NAME))
                .description(record.get(SUBSTORIES.DESCRIPTION))
                .parentId(record.get(SUBSTORIES.PARENT_ID))
                .createdAt(record.get(SUBSTORIES.CREATED_AT))
                .code(record.get(SUBSTORIES.CODE))
                .createdBy(createdByUser)
                .assignedTo(assignedToUser)
                .state(state)
                .type(type)
                .build();
    }

    private String getSubstoryCode(UUID projectId) {
        final var record = dslContext
                .select(
                    DSL.field(DSL.count(STORIES.ID).plus(DSL.count(SUBSTORIES.ID))).as("total_count"),
                    PROJECTS.CODE
                )
                .from(PROJECTS)
                .join(STORIES).on(PROJECTS.ID.eq(STORIES.PROJECT_ID))
                .leftJoin(SUBSTORIES).on(SUBSTORIES.PARENT_ID.eq(STORIES.ID))
                .where(PROJECTS.ID.eq(projectId))
                .groupBy(PROJECTS.CODE)
                .fetchOne();

        if(record == null) {
            throw PROJECT_NOT_FOUND.createError(projectId.toString()).convertToException();
        }

        return record.get(PROJECTS.CODE) + "-" + (Integer.parseInt(record.get("total_count").toString()) + 1);
    }

    private UUID getProjectIdByStoryId(UUID storyId) {
        final var record = dslContext
                .select(PROJECTS.ID)
                .from(PROJECTS)
                .join(STORIES).on(PROJECTS.ID.eq(STORIES.PROJECT_ID))
                .where(STORIES.ID.eq(storyId))
                .fetchOne();

        if(record == null) {
            throw PROJECT_BY_STORY_ID_NOT_FOUND.createError(storyId.toString()).convertToException();
        }

        return record.get(PROJECTS.ID);
    }
}
