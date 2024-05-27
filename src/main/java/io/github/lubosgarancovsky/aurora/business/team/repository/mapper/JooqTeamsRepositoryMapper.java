package io.github.lubosgarancovsky.aurora.business.team.repository.mapper;

import io.github.lubosgarancovsky.aurora.domain.team.entity.ImmutableTeamEntity;
import io.github.lubosgarancovsky.aurora.domain.team.entity.TeamEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutablePublicUserResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import org.jooq.Record;
import org.jooq.Result;

import java.util.LinkedList;
import java.util.List;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Teams.TEAMS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;

public class JooqTeamsRepositoryMapper {

    public static TeamEntity map(Result result) {
        Record team = (Record) result.get(0);

        return ImmutableTeamEntity.builder()
                .id(team.get(TEAMS.ID))
                .projectId(team.get(TEAMS.PROJECT_ID))
                .createdBy(team.get(TEAMS.CREATED_BY))
                .members(result.stream().map(record -> {
                    return ImmutablePublicUserResponse.builder()
                            .id(((Record) record).get(PARTNERS.ID).toString())
                            .name(((Record) record).get(PARTNERS.NAME))
                            .email(((Record) record).get(PARTNERS.EMAIL))
                            .color(((Record) record).get(PARTNERS.COLOR))
                            .picture(((Record) record).get(PARTNERS.PICTURE))
                            .build();
                }).toList())
                .build();
    }
}
