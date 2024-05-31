package io.github.lubosgarancovsky.aurora.domain.story.entity;

import io.github.lubosgarancovsky.aurora.domain.board.entity.StoryStateEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value.Immutable
public interface SubstoryEntity {

    UUID id();
    UUID parentId();
    String name();
    String description();
    String code();
    StoryStateEntity state();
    StoryTypeEntity type();

    @Nullable
    PublicUserResponse assignedTo();
    PublicUserResponse createdBy();

    LocalDateTime createdAt();
}
