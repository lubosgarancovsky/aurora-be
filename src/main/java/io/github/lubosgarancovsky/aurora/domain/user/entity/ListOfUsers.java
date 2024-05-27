package io.github.lubosgarancovsky.aurora.domain.user.entity;

import org.immutables.value.Value;

import io.github.lubosgarancovsky.domain.listing.PaginatedResult;

@Value.Immutable
public interface ListOfUsers extends PaginatedResult<UserEntity> {
    
}
