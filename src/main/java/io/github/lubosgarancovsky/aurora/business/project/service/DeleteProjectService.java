package io.github.lubosgarancovsky.aurora.business.project.service;

import io.github.lubosgarancovsky.aurora.business.project.repository.JooqProjectRepository;
import io.github.lubosgarancovsky.aurora.business.project.usecase.DeleteProjectUseCase;
import io.github.lubosgarancovsky.aurora.domain.project.command.DeleteProjectCommand;
import org.springframework.stereotype.Service;

@Service
public class DeleteProjectService implements DeleteProjectUseCase {

    private final JooqProjectRepository jooqProjectRepository;

    public DeleteProjectService(JooqProjectRepository jooqProjectRepository) {
        this.jooqProjectRepository = jooqProjectRepository;
    }

    @Override
    public Void execute(DeleteProjectCommand deleteProjectCommand) {
        this.jooqProjectRepository.delete(deleteProjectCommand);
        return null;
    }
}
