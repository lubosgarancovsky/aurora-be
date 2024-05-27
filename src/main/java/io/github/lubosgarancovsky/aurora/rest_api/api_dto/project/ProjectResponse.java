package io.github.lubosgarancovsky.aurora.rest_api.api_dto.project;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import org.immutables.value.Value;

import java.time.LocalDateTime;

@JsonSerialize(as = ImmutableProjectResponse.class)
@JsonDeserialize(as = ImmutableProjectResponse.class)
@Value.Immutable
public interface ProjectResponse {

    String id();

    String name();

    String code();

    String description();

    LocalDateTime createdAt();

    PublicUserResponse createdBy();
}
