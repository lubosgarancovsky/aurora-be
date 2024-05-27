package io.github.lubosgarancovsky.aurora.rest_api.api_dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableEntityCreatedResponse.class)
@JsonDeserialize(as = ImmutableEntityCreatedResponse.class)
@Value.Immutable
public interface EntityCreatedResponse {

    String id();
}
