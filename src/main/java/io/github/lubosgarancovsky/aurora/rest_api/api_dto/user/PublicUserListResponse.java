package io.github.lubosgarancovsky.aurora.rest_api.api_dto.user;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.github.lubosgarancovsky.restapi.dto.listing.ListingResponse;

@JsonSerialize(as = ImmutablePublicUserListResponse.class)
@JsonDeserialize(as = ImmutablePublicUserListResponse.class)
@Value.Immutable
public interface PublicUserListResponse extends ListingResponse<PublicUserResponse> {
    
}
