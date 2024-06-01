package io.github.lubosgarancovsky.aurora.business.story.repository;

import io.github.lubosgarancovsky.aurora.business.JooqRepository;
import io.github.lubosgarancovsky.aurora.business.story.repository.mapper.JooqStoryRepositoryMapper;
import io.github.lubosgarancovsky.aurora.domain.story.command.CreateStoryCommand;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Stories.STORIES;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.StoryType.STORY_TYPE;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Substories.SUBSTORIES;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.StoryState.STORY_STATE;

import io.github.lubosgarancovsky.aurora.domain.story.command.UpdateStoryCommand;
import io.github.lubosgarancovsky.aurora.domain.story.entity.ImmutableStoryTypeEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryTypeEntity;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryDetailQuery;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryListingQuery;
import io.github.lubosgarancovsky.aurora.repository.model.tables.Partners;
import io.github.lubosgarancovsky.aurora.repository.model.tables.StoryState;
import io.github.lubosgarancovsky.aurora.repository.model.tables.StoryType;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.StoryTypeRecord;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.ImmutableEntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.ListOfStories;
import io.github.lubosgarancovsky.persistance.jooq.handler.JooqPaginatedSelectQueryHandler;
import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.PaginatedResultMapper;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import static io.github.lubosgarancovsky.aurora.business.story.repository.mapper.JooqStoryRepositoryMapper.listingMapper;
import static io.github.lubosgarancovsky.aurora.rest_api.error.StoryErrorCode.STORY_NOT_FOUND;

import java.util.List;
import java.util.UUID;

@Repository
public class JooqStoryRepository extends JooqRepository {

    protected JooqStoryRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public EntityCreatedResponse insert(CreateStoryCommand command) {
        Integer count = this.count(command.project().id());
        String code = String.format("%s-%d", command.project().code(), count + 1);
        UUID todoId = this.stateIdByCode("to-do");

        Record record = dslContext.insertInto(STORIES)
                .set(STORIES.NAME, command.name())
                .set(STORIES.DESCRIPTION, command.description())
                .set(STORIES.CODE, code)
                .set(STORIES.PROJECT_ID, command.project().id())
                .set(STORIES.IN_BOARD, command.inBoard())
                .set(STORIES.STATE_ID, todoId)
                .set(STORIES.TYPE_ID, command.typeId())
                .set(STORIES.CREATED_BY, command.userId())
                .set(STORIES.ASSIGNED_TO, command.assigneeId().orElse(null))
                .returning()
                .fetchOne();

        if(record == null) {
            throw new RuntimeException("Could not create story");
        }

        return ImmutableEntityCreatedResponse.builder()
                .id(record.get(STORIES.ID).toString())
                .build();
    }

    public ListOfStories list(StoryListingQuery query) {
        final var stories = baseSelect();

        final JooqPaginatedSelectQueryHandler<StoryListingQuery, ListOfStories>
                paginatedListingHandler =
                new JooqPaginatedSelectQueryHandler<>(
                        query, new StoryJooqRsqlMetadataConfigProvider());

        final PaginatedResultMapper<StoryListingQuery, ListOfStories> paginated =
                paginatedListingHandler.handle(stories.getQuery());

        return paginated.map(listingMapper);
    }

    public List<StoryTypeEntity> listStoryTypes() {
         final var types = dslContext
                .select(STORY_TYPE.ID, STORY_TYPE.NAME, STORY_TYPE.CODE)
                .from(STORY_TYPE)
                .fetch();

         return types.stream().map(this::map).toList();
    }

    public void update(UpdateStoryCommand command) {
        dslContext.update(STORIES)
                .set(STORIES.NAME, command.name())
                .set(STORIES.DESCRIPTION, command.description())
                .set(STORIES.STATE_ID, command.stateId())
                .set(STORIES.TYPE_ID, command.typeId())
                .where(STORIES.ID.eq(command.storyId()))
                .execute();
    }

    public StoryEntity detail(StoryDetailQuery query) {
        final var stories = baseSelect()
                .where(STORIES.ID.eq(query.storyId()))
                .fetch();

        if(stories.isEmpty()) {
            throw STORY_NOT_FOUND.createError(query.storyId().toString()).convertToException();
        }

        return JooqStoryRepositoryMapper.map(stories.stream()).get(0);
    }

    private Integer count(UUID projectId) {
        Select<Record1<UUID>> storyCount = dslContext.select(STORIES.ID)
                .from(STORIES)
                .where(STORIES.PROJECT_ID.eq(projectId));

        Select<Record1<UUID>> substoryCount = dslContext.select(SUBSTORIES.ID)
                .from(SUBSTORIES)
                .join(STORIES)
                .on(SUBSTORIES.PARENT_ID.eq(STORIES.ID))
                .where(STORIES.PROJECT_ID.eq(projectId));

        var combinedCount = dslContext
                .selectCount()
                .from(storyCount.unionAll(substoryCount).asTable("combined"));

        return combinedCount.fetchOne(0, Integer.class);
    }

    private UUID stateIdByCode(String code) {
        return dslContext
                .select(STORY_STATE.ID)
                .from(STORY_STATE)
                .where(STORY_STATE.CODE.eq(code))
                .fetchOne(0, UUID.class);
    }

    private StoryTypeEntity map(Record3<UUID, String, String> record) {
        return ImmutableStoryTypeEntity.builder()
                .id(record.get(0, UUID.class))
                .name(record.get(1, String.class))
                .code(record.get(2, String.class))
                .build();
    }

    private SelectOnConditionStep<Record> baseSelect() {
        Partners cb = PARTNERS.as("createdBy");
        Partners as = PARTNERS.as("assignedTo");
        Partners subCb = PARTNERS.as("subStoryCreatedBy");
        Partners subAs = PARTNERS.as("subStoryAssignedTo");
        StoryState storyState = STORY_STATE.as("storyState");
        StoryType storyType = STORY_TYPE.as("storyType");
        StoryState subStoryState = STORY_STATE.as("subStoryState");
        StoryType subStoryType = STORY_TYPE.as("subStoryType");

        return dslContext.select(
                        STORIES.ID.as("storyId"),
                        STORIES.NAME.as("storyName"),
                        STORIES.DESCRIPTION.as("storyDescription"),
                        STORIES.CODE.as("storyCode"),
                        STORIES.IN_BOARD.as("storyInBoard"),
                        STORIES.CREATED_AT.as("storyCreatedAt"),
                        storyState.ID.as("storyStateId"),
                        storyState.NAME.as("storyStateName"),
                        storyState.CODE.as("storyStateCode"),
                        storyType.ID.as("storyTypeId"),
                        storyType.NAME.as("storyTypeName"),
                        storyType.CODE.as("storyTypeCode"),
                        cb.ID.as("createdById"),
                        cb.NAME.as("createdByName"),
                        cb.EMAIL.as("createdByEmail"),
                        cb.COLOR.as("createdByColor"),
                        cb.PICTURE.as("createdByPicture"),
                        as.ID.as("assignedToId"),
                        as.NAME.as("assignedToName"),
                        as.EMAIL.as("assignedToEmail"),
                        as.COLOR.as("assignedToColor"),
                        as.PICTURE.as("assignedToPicture"),
                        SUBSTORIES.ID.as("subStoryId"),
                        SUBSTORIES.NAME.as("subStoryName"),
                        SUBSTORIES.DESCRIPTION.as("subStoryDescription"),
                        SUBSTORIES.CODE.as("subStoryCode"),
                        SUBSTORIES.CREATED_AT.as("subStoryCreatedAt"),
                        SUBSTORIES.PARENT_ID.as("subStoryParentId"),
                        subStoryState.ID.as("subStoryStateId"),
                        subStoryState.NAME.as("subStoryStateName"),
                        subStoryState.CODE.as("subStoryStateCode"),
                        subStoryType.ID.as("subStoryTypeId"),
                        subStoryType.NAME.as("subStoryTypeName"),
                        subStoryType.CODE.as("subStoryTypeCode"),
                        subCb.ID.as("subStoryCreatedById"),
                        subCb.NAME.as("subStoryCreatedByName"),
                        subCb.EMAIL.as("subStoryCreatedByEmail"),
                        subCb.COLOR.as("subStoryCreatedByColor"),
                        subCb.PICTURE.as("subStoryCreatedByPicture"),
                        subAs.ID.as("subStoryAssignedToId"),
                        subAs.NAME.as("subStoryAssignedToName"),
                        subAs.EMAIL.as("subStoryAssignedToEmail"),
                        subAs.COLOR.as("subStoryAssignedToColor"),
                        subAs.PICTURE.as("subStoryAssignedToPicture")
                )
                .from(STORIES)
                .join(storyState).on(storyState.ID.eq(STORIES.STATE_ID))
                .join(storyType).on(storyType.ID.eq(STORIES.TYPE_ID))
                .join(cb).on(cb.ID.eq(STORIES.CREATED_BY))
                .leftJoin(as).on(as.ID.eq(STORIES.ASSIGNED_TO))
                .leftJoin(SUBSTORIES).on(SUBSTORIES.PARENT_ID.eq(STORIES.ID))
                .leftJoin(subStoryState).on(subStoryState.ID.eq(SUBSTORIES.STATE_ID))
                .leftJoin(subStoryType).on(subStoryType.ID.eq(SUBSTORIES.TYPE_ID))
                .leftJoin(subCb).on(subCb.ID.eq(SUBSTORIES.CREATED_BY))
                .leftJoin(subAs).on(subAs.ID.eq(SUBSTORIES.ASSIGNED_TO));
    }
}
