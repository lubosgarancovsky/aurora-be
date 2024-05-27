package io.github.lubosgarancovsky.aurora.rest_api.api_dto.invitation;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.aurora.domain.invitation.entity.InvitationEntity;
import io.github.lubosgarancovsky.restapi.dto.listing.ListingResponse;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableInvitationListResponse.class)
@JsonDeserialize(as = ImmutableInvitationListResponse.class)
@Value.Immutable
public interface InvitationListResponse extends ListingResponse<InvitationEntity> {
}
