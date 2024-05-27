package io.github.lubosgarancovsky.aurora.business.project.usecase;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ListOfProjects;
import io.github.lubosgarancovsky.aurora.domain.project.query.ProjectListingQuery;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface ListProjectsUseCase extends UseCaseQuery<ProjectListingQuery, ListOfProjects> {
}
