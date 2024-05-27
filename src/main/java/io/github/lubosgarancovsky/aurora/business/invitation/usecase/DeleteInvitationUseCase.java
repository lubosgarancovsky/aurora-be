package io.github.lubosgarancovsky.aurora.business.invitation.usecase;

import io.github.lubosgarancovsky.aurora.domain.invitation.command.DeleteInvitationCommand;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface DeleteInvitationUseCase extends UseCaseCommand<DeleteInvitationCommand, Void> {
}
