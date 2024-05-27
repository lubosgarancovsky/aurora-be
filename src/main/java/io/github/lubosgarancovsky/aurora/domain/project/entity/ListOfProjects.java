package io.github.lubosgarancovsky.aurora.domain.project.entity;

import io.github.lubosgarancovsky.domain.listing.PaginatedResult;
import org.immutables.value.Value;

@Value.Immutable
public interface ListOfProjects extends PaginatedResult<ProjectEntity> {
}
