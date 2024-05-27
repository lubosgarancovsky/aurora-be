package io.github.lubosgarancovsky.aurora.business.user.usecase;

import io.github.lubosgarancovsky.aurora.domain.user.command.RegisterUserCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.TokensResponse;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface RegisterUserUseCase extends UseCaseCommand<RegisterUserCommand, TokensResponse> {
}
