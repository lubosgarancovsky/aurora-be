package io.github.lubosgarancovsky.aurora.rest_api.api_dto.project;

import org.immutables.value.Value;

@Value.Immutable
public interface ProjectLinkRequest {

    String name();

    String url();
}
