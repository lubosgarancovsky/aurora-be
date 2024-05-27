package io.github.lubosgarancovsky.aurora.domain.team.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import org.immutables.value.Value;

import java.util.List;
import java.util.UUID;

@JsonSerialize(as = ImmutableTeamEntity.class)
@JsonDeserialize(as = ImmutableTeamEntity.class)
@Value.Immutable
public interface TeamEntity {

    UUID id();

    UUID projectId();

    UUID createdBy();

    List<PublicUserResponse> members();
}
