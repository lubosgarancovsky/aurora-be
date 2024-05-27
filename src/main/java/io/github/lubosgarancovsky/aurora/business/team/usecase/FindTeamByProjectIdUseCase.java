package io.github.lubosgarancovsky.aurora.business.team.usecase;

import io.github.lubosgarancovsky.aurora.domain.team.command.FindTeamByProjectIdCommand;
import io.github.lubosgarancovsky.aurora.domain.team.entity.TeamEntity;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface FindTeamByProjectIdUseCase extends UseCaseCommand<FindTeamByProjectIdCommand, TeamEntity> {
}
