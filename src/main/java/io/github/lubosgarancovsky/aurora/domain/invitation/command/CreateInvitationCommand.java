package io.github.lubosgarancovsky.aurora.domain.invitation.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface CreateInvitationCommand extends Command {

    UUID senderId();

    UUID recipientId();

    UUID teamId();
}
