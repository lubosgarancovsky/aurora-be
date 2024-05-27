package io.github.lubosgarancovsky.aurora.business.project.usecase;

import io.github.lubosgarancovsky.aurora.domain.project.command.CreateProjectCommand;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectCreatedEntity;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface CreateProjectUseCase extends UseCaseCommand<CreateProjectCommand, ProjectCreatedEntity> {
}
