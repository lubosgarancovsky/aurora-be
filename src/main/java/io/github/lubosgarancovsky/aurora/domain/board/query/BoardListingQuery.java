package io.github.lubosgarancovsky.aurora.domain.board.query;

import io.github.lubosgarancovsky.domain.marker.Query;
import org.immutables.value.Value;

import java.util.UUID;


@Value.Immutable
public interface BoardListingQuery extends Query {
    UUID projectId();
}
