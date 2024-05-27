package io.github.lubosgarancovsky.aurora.rest_api.api_dto.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableLoginRequest.class)
@JsonDeserialize(as = ImmutableLoginRequest.class)
@Value.Immutable
public interface LoginRequest {
    String email();

    String password();
}
