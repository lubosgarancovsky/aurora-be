package io.github.lubosgarancovsky.aurora.domain.project.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import io.github.lubosgarancovsky.domain.listing.PaginatedResultItem;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.UUID;


@JsonSerialize(as = ImmutableProjectEntity.class)
@JsonDeserialize(as = ImmutableProjectEntity.class)
@Value.Immutable
public interface ProjectEntity extends PaginatedResultItem {

    UUID id();

    String name();

    String code();

    String description();

    PublicUserResponse createdBy();

    LocalDateTime createdAt();
}
