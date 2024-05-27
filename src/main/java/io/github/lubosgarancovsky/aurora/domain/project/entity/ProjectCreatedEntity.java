package io.github.lubosgarancovsky.aurora.domain.project.entity;


import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface ProjectCreatedEntity {

    UUID id();
}
