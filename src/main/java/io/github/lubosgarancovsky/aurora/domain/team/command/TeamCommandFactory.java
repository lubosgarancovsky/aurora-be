package io.github.lubosgarancovsky.aurora.domain.team.command;

import java.util.UUID;

public class TeamCommandFactory {

    public static CreateTeamCommand createTeamCommand(String id, UUID userId) {
        return ImmutableCreateTeamCommand.builder()
                .userId(userId)
                .projectId(UUID.fromString(id))
                .build();
    }

    public static FindTeamByProjectIdCommand findTeamByProjectIdCommand(String id) {
        return ImmutableFindTeamByProjectIdCommand.builder()
                .projectId(UUID.fromString(id))
                .build();
    }
}
