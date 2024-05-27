package io.github.lubosgarancovsky.aurora.domain.project.query;

import io.github.lubosgarancovsky.domain.marker.Query;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface FindProjectByIdQuery extends Query {

    UUID projectId();

    UUID userId();
}
