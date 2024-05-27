package io.github.lubosgarancovsky.aurora.domain.invitation.command;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.invitation.InvitationRequest;

import java.util.UUID;

public class InvitationCommandFactory {

    public static CreateInvitationCommand createInvitationCommand(String userId, InvitationRequest request) {
        return ImmutableCreateInvitationCommand.builder()
                .senderId(UUID.fromString(userId))
                .recipientId(UUID.fromString(request.recipientId()))
                .teamId(UUID.fromString(request.teamId()))
                .build();
    }

    public static DeleteInvitationCommand createDeleteInvitationCommand(String userId, String id) {
        return ImmutableDeleteInvitationCommand.builder()
                .userId(UUID.fromString(userId))
                .invitationId(UUID.fromString(id))
                .build();
    }

    public static AcceptInvitationCommand createAcceptInvitationCommand(String userId, String id) {
        return ImmutableAcceptInvitationCommand.builder()
                .userId(UUID.fromString(userId))
                .invitationId(UUID.fromString(id))
                .build();
    }
}
