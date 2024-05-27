package io.github.lubosgarancovsky.aurora.domain.invitation.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface AcceptInvitationCommand extends Command {

    UUID invitationId();

    UUID userId();
}
