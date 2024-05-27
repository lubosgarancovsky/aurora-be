package io.github.lubosgarancovsky.aurora.business.project.repository;

import io.github.lubosgarancovsky.aurora.business.JooqRepository;
import io.github.lubosgarancovsky.aurora.domain.project.command.CreateProjectCommand;
import io.github.lubosgarancovsky.aurora.domain.project.command.DeleteProjectCommand;
import io.github.lubosgarancovsky.aurora.domain.project.entity.*;
import io.github.lubosgarancovsky.aurora.domain.project.query.FindProjectByIdQuery;
import io.github.lubosgarancovsky.aurora.domain.project.query.ProjectListingQuery;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.ProjectsRecord;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutablePublicUserResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import io.github.lubosgarancovsky.persistance.jooq.handler.JooqPaginatedSelectQueryHandler;
import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.PaginatedResultMapper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static io.github.lubosgarancovsky.aurora.business.project.repository.mapper.JooqProjectRepositoryMapper.listingMapper;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Projects.PROJECTS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Teams.TEAMS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.TeamPartner.TEAM_PARTNER;
import static io.github.lubosgarancovsky.aurora.rest_api.error.ProjectErrorCode.PROJECT_NOT_FOUND;


@Repository
public class JooqProjectRepository extends JooqRepository {

    protected JooqProjectRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public ProjectCreatedEntity insert(CreateProjectCommand command) {
        final ProjectsRecord record = dslContext.insertInto(PROJECTS)
                .set(PROJECTS.NAME, command.name())
                .set(PROJECTS.DESCRIPTION, command.description())
                .set(PROJECTS.CODE, command.code())
                .set(PROJECTS.CREATED_AT, LocalDateTime.now())
                .set(PROJECTS.CREATED_BY, command.createdBy())
                .returning()
                .fetchOne();

        if(record == null) {
            throw new RuntimeException("Could not create project");
        }

        return ImmutableProjectCreatedEntity.builder()
                .id(record.getId())
                .build();
    }

    public ListOfProjects list(ProjectListingQuery query) {
        final var projects = dslContext.selectDistinct(
                PROJECTS.ID,
                PROJECTS.NAME,
                PROJECTS.DESCRIPTION,
                PROJECTS.CODE,
                PROJECTS.CREATED_AT,
                PARTNERS.ID,
                PARTNERS.NAME,
                PARTNERS.EMAIL,
                PARTNERS.COLOR,
                PARTNERS.PICTURE,
                PARTNERS.UPDATED_AT,
                PARTNERS.CREATED_AT
                )
                .from(PROJECTS)
                .leftJoin(TEAMS).on(PROJECTS.ID.eq(TEAMS.PROJECT_ID))
                .leftJoin(TEAM_PARTNER).on(TEAMS.ID.eq(TEAM_PARTNER.TEAM_ID))
                .leftJoin(PARTNERS).on(PROJECTS.CREATED_BY.eq(PARTNERS.ID))
                .where(PROJECTS.CREATED_BY.eq(query.userId()))
                        .or(TEAM_PARTNER.PARTNER_ID.eq(query.userId()));

        final JooqPaginatedSelectQueryHandler<ProjectListingQuery, ListOfProjects>
                paginatedListingHandler =
                new JooqPaginatedSelectQueryHandler<>(
                        query, new ProjectJooqRsqlMetadataConfigProvider());

        final PaginatedResultMapper<ProjectListingQuery, ListOfProjects> paginated =
                paginatedListingHandler.handle(projects.getQuery());

        return paginated.map(listingMapper);
    }
    public ProjectEntity findOne(FindProjectByIdQuery query) {
        final var project = dslContext
                .selectDistinct(
                        PROJECTS.ID,
                        PROJECTS.NAME,
                        PROJECTS.DESCRIPTION,
                        PROJECTS.CODE,
                        PROJECTS.CREATED_AT,
                        PARTNERS.ID,
                        PARTNERS.NAME,
                        PARTNERS.EMAIL,
                        PARTNERS.COLOR,
                        PARTNERS.PICTURE,
                        PARTNERS.UPDATED_AT,
                        PARTNERS.CREATED_AT
                )
                .from(PROJECTS)
                .leftJoin(TEAMS).on(PROJECTS.ID.eq(TEAMS.PROJECT_ID))
                .leftJoin(TEAM_PARTNER).on(TEAMS.ID.eq(TEAM_PARTNER.TEAM_ID))
                .leftJoin(PARTNERS).on(PROJECTS.CREATED_BY.eq(PARTNERS.ID))
                .where(PROJECTS.ID.eq(query.projectId())
                        .and(TEAM_PARTNER.PARTNER_ID.eq(query.userId())
                                .or(PROJECTS.CREATED_BY.eq(query.userId()))))
                .fetchOne();

        if(project == null) {
            throw PROJECT_NOT_FOUND.createError(query.projectId().toString()).convertToException();
        }

        return map(project);
    }

    public void delete(DeleteProjectCommand command) {
        dslContext.delete(PROJECTS)
                .where(PROJECTS.ID.eq(command.id()))
                .and(PROJECTS.CREATED_BY.eq(command.userId()))
                .execute();
    }

    private ProjectEntity map(Record record) {

        PublicUserResponse createdBy = ImmutablePublicUserResponse.builder()
                .id(record.get(PARTNERS.ID).toString())
                .name(record.get(PARTNERS.NAME))
                .email(record.get(PARTNERS.EMAIL))
                .color(record.get(PARTNERS.COLOR))
                .picture(record.get(PARTNERS.PICTURE))
                .build();

        return ImmutableProjectEntity.builder()
                .id(record.get(PROJECTS.ID))
                .name(record.get(PROJECTS.NAME))
                .description(record.get(PROJECTS.DESCRIPTION))
                .code(record.get(PROJECTS.CODE))
                .createdAt(record.get(PROJECTS.CREATED_AT))
                .createdBy(createdBy)
                .build();
    }
}
