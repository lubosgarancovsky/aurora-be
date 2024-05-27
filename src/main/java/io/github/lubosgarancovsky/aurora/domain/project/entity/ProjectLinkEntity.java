package io.github.lubosgarancovsky.aurora.domain.project.entity;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface ProjectLinkEntity {

    UUID id();

    UUID projectId();

    String name();

    String url();
}
