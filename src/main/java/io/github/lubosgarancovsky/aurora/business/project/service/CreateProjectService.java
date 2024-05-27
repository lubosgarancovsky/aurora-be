package io.github.lubosgarancovsky.aurora.business.project.service;

import io.github.lubosgarancovsky.aurora.business.project.repository.JooqProjectRepository;
import io.github.lubosgarancovsky.aurora.business.project.usecase.CreateProjectUseCase;
import io.github.lubosgarancovsky.aurora.domain.project.command.CreateProjectCommand;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectCreatedEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectService implements CreateProjectUseCase {

    private final JooqProjectRepository jooqProjectRepository;

    public CreateProjectService(JooqProjectRepository jooqProjectRepository) {
        this.jooqProjectRepository = jooqProjectRepository;
    }

    @Override
    public ProjectCreatedEntity execute(CreateProjectCommand createProjectCommand) {
        return this.jooqProjectRepository.insert(createProjectCommand);
    }
}
