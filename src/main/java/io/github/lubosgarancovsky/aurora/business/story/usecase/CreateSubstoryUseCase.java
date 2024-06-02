package io.github.lubosgarancovsky.aurora.business.story.usecase;

import io.github.lubosgarancovsky.aurora.domain.story.command.CreateSubstoryCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface CreateSubstoryUseCase extends UseCaseCommand<CreateSubstoryCommand, EntityCreatedResponse> {
}
