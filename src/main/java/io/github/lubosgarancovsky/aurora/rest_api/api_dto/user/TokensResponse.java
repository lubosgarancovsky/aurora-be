package io.github.lubosgarancovsky.aurora.rest_api.api_dto.user;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableTokensResponse.class)
@JsonDeserialize(as = ImmutableTokensResponse.class)
@Value.Immutable
public interface TokensResponse {

    String accessToken();

    String refreshToken();
}
