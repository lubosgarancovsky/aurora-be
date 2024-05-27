package io.github.lubosgarancovsky.aurora.domain.invitation.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import io.github.lubosgarancovsky.domain.listing.PaginatedResultItem;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.UUID;


@JsonSerialize(as = ImmutableInvitationEntity.class)
@JsonDeserialize(as = ImmutableInvitationEntity.class)
@Value.Immutable
public interface InvitationEntity extends PaginatedResultItem {

    UUID id();

    UUID teamId();

    PublicUserResponse sender();

    PublicUserResponse recipient();

    UUID projectId();

    String projectName();

    LocalDateTime createdAt();
}
