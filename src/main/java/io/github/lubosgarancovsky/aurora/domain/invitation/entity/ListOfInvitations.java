package io.github.lubosgarancovsky.aurora.domain.invitation.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.domain.listing.PaginatedResult;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableListOfInvitations.class)
@JsonDeserialize(as = ImmutableListOfInvitations.class)
@Value.Immutable
public interface ListOfInvitations extends PaginatedResult<InvitationEntity> {
}
