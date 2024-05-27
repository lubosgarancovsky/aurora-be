package io.github.lubosgarancovsky.aurora.business.project.service;

import io.github.lubosgarancovsky.aurora.business.project.repository.JooqProjectRepository;
import io.github.lubosgarancovsky.aurora.business.project.usecase.ListProjectsUseCase;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ListOfProjects;
import io.github.lubosgarancovsky.aurora.domain.project.query.ProjectListingQuery;
import org.springframework.stereotype.Service;

@Service
public class ListProjectService implements ListProjectsUseCase {

    private final JooqProjectRepository jooqProjectRepository;

    public ListProjectService(JooqProjectRepository jooqProjectRepository) {
        this.jooqProjectRepository = jooqProjectRepository;
    }

    @Override
    public ListOfProjects execute(ProjectListingQuery projectListingQuery) {
        return this.jooqProjectRepository.list(projectListingQuery);
    }
}
