package io.github.lubosgarancovsky.aurora.domain.story.entity;

import io.github.lubosgarancovsky.aurora.domain.board.entity.StoryStateEntity;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import io.github.lubosgarancovsky.domain.listing.PaginatedResultItem;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface StoryEntity extends PaginatedResultItem {

    UUID id();
    String name();
    String description();
    String code();
    Boolean inBoard();

    StoryStateEntity state();

    StoryTypeEntity type();

    @Nullable
    PublicUserResponse assignedTo();
    PublicUserResponse createdBy();

    LocalDateTime createdAt();

    List<SubstoryEntity> substories();
}
