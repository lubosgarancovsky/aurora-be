package io.github.lubosgarancovsky.aurora.domain.story.query;

import io.github.lubosgarancovsky.domain.marker.Query;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface SubstoryDetailQuery extends Query {
    UUID substoryId();
}
