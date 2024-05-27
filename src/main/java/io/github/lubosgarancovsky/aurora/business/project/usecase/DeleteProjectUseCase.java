package io.github.lubosgarancovsky.aurora.business.project.usecase;

import io.github.lubosgarancovsky.aurora.domain.project.command.DeleteProjectCommand;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface DeleteProjectUseCase extends UseCaseCommand<DeleteProjectCommand, Void> {
}
