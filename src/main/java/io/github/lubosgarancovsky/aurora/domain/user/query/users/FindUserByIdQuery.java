package io.github.lubosgarancovsky.aurora.domain.user.query.users;

import java.util.UUID;

import org.immutables.value.Value;

import io.github.lubosgarancovsky.domain.marker.Query;

@Value.Immutable
public abstract class FindUserByIdQuery implements Query {
    
    @Value.Parameter
    public abstract UUID id();

    public static FindUserByIdQuery of(UUID id) {
        return ImmutableFindUserByIdQuery.of(id);
    }

}
