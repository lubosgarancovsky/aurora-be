package io.github.lubosgarancovsky.aurora.business.team.repository;

import io.github.lubosgarancovsky.aurora.business.JooqRepository;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Teams.TEAMS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.TeamPartner.TEAM_PARTNER;

import io.github.lubosgarancovsky.aurora.business.team.repository.mapper.JooqTeamsRepositoryMapper;
import io.github.lubosgarancovsky.aurora.domain.team.command.CreateTeamCommand;
import io.github.lubosgarancovsky.aurora.domain.team.command.FindTeamByProjectIdCommand;
import io.github.lubosgarancovsky.aurora.domain.team.entity.TeamEntity;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.TeamsRecord;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.ImmutableEntityCreatedResponse;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JooqTeamRepository extends JooqRepository {

    protected JooqTeamRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public EntityCreatedResponse insert(CreateTeamCommand command) {
        TeamsRecord record = dslContext.insertInto(TEAMS)
                .set(TEAMS.PROJECT_ID, command.projectId())
                .set(TEAMS.CREATED_BY, command.userId())
                .onConflictDoNothing()
                .returning()
                .fetchOne();

        if(record == null) {
            throw new RuntimeException("Could not create team");
        }

        return ImmutableEntityCreatedResponse.builder()
                .id(record.getId().toString())
                .build();
    }

    public TeamEntity findByProjectId(FindTeamByProjectIdCommand command) {
        final var team = dslContext
                .select(
                        TEAMS.ID,
                        TEAMS.PROJECT_ID,
                        TEAMS.CREATED_BY,
                        PARTNERS.ID,
                        PARTNERS.EMAIL,
                        PARTNERS.NAME,
                        PARTNERS.COLOR,
                        PARTNERS.PICTURE
                ).from(TEAMS)
                .join(TEAM_PARTNER).on(TEAMS.ID.eq(TEAM_PARTNER.TEAM_ID))
                .join(PARTNERS).on(TEAM_PARTNER.PARTNER_ID.eq(PARTNERS.ID))
                .where(TEAMS.PROJECT_ID.eq(command.projectId()))
                .fetch();

        return JooqTeamsRepositoryMapper.map(team);
    }

    public boolean existsByProjectId(UUID projectId) {
        return dslContext.fetchExists(dslContext.selectFrom(TEAMS).where(TEAMS.PROJECT_ID.eq(projectId)));
    }
}
