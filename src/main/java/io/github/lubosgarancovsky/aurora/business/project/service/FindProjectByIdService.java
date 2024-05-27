package io.github.lubosgarancovsky.aurora.business.project.service;

import io.github.lubosgarancovsky.aurora.business.project.repository.JooqProjectRepository;
import io.github.lubosgarancovsky.aurora.business.project.usecase.FindProjectByIdUseCase;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.aurora.domain.project.query.FindProjectByIdQuery;
import org.springframework.stereotype.Service;

@Service
public class FindProjectByIdService implements FindProjectByIdUseCase {

    private final JooqProjectRepository jooqProjectRepository;

    public FindProjectByIdService(JooqProjectRepository jooqProjectRepository) {
        this.jooqProjectRepository = jooqProjectRepository;
    }

    @Override
    public ProjectEntity execute(FindProjectByIdQuery query) {
        return this.jooqProjectRepository.findOne(query);
    }
}
