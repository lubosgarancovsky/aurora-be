package io.github.lubosgarancovsky.aurora.business.project.usecase;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.aurora.domain.project.query.FindProjectByIdQuery;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface FindProjectByIdUseCase extends UseCaseQuery<FindProjectByIdQuery, ProjectEntity> {
}
