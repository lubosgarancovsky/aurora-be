package io.github.lubosgarancovsky.aurora.business.invitation.usecase;

import io.github.lubosgarancovsky.aurora.domain.invitation.command.AcceptInvitationCommand;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface AcceptInvitationUseCase extends UseCaseCommand<AcceptInvitationCommand, Void> {
}
