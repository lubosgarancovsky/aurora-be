package io.github.lubosgarancovsky.aurora.business.user.usecase;

import io.github.lubosgarancovsky.aurora.domain.user.command.DeleteUserCommand;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface DeleteUserUseCase extends UseCaseCommand<DeleteUserCommand, String> {
}
