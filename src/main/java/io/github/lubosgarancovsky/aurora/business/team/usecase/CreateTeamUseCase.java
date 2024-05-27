package io.github.lubosgarancovsky.aurora.business.team.usecase;

import io.github.lubosgarancovsky.aurora.domain.team.command.CreateTeamCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface CreateTeamUseCase extends UseCaseCommand<CreateTeamCommand, EntityCreatedResponse> {
}
