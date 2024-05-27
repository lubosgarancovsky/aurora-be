package io.github.lubosgarancovsky.aurora.rest_api.api_dto.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableRegisterRequest.class)
@JsonDeserialize(as = ImmutableRegisterRequest.class)
@Value.Immutable
public interface RegisterRequest {

    String name();

    String email();

    String password();

    String color();
}
