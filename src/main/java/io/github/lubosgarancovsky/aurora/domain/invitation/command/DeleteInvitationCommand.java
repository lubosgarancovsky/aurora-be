package io.github.lubosgarancovsky.aurora.domain.invitation.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface DeleteInvitationCommand extends Command {

    UUID userId();

    UUID invitationId();
}
