package io.github.lubosgarancovsky.aurora.rest_api.api_dto.user;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.github.lubosgarancovsky.domain.listing.PaginatedResultItem;


@JsonSerialize(as = ImmutablePublicUserResponse.class)
@JsonDeserialize(as = ImmutablePublicUserResponse.class)
@Value.Immutable
public interface PublicUserResponse extends PaginatedResultItem {

    String id();
    
    String name();

    String email();

    @Nullable
    String picture();

    String color();

}
