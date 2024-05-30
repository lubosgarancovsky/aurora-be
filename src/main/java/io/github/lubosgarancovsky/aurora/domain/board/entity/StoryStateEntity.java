package io.github.lubosgarancovsky.aurora.domain.board.entity;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface StoryStateEntity {

    UUID id();

    String name();

    String code();
}
