package io.github.lubosgarancovsky.aurora.rest_api.api_dto.invitation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableInvitationRequest.class)
@JsonDeserialize(as = ImmutableInvitationRequest.class)
@Value.Immutable
public interface InvitationRequest {

    String teamId();
    String recipientId();
}
