package io.github.lubosgarancovsky.aurora.domain.user.entity;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import io.github.lubosgarancovsky.domain.listing.PaginatedResultItem;

import java.time.LocalDateTime;
import java.util.UUID;

@Value.Immutable
public interface UserEntity extends PaginatedResultItem {
    UUID id();

    String name();

    String email();

    String password();

    String color();

    @Nullable
    String picture();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();
}
