package io.github.lubosgarancovsky.aurora.domain.story.entity;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface StoryTypeEntity {

    UUID id();
    String name();
    String code();
}
