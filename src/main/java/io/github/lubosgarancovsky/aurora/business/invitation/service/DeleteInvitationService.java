package io.github.lubosgarancovsky.aurora.business.invitation.service;

import io.github.lubosgarancovsky.aurora.business.invitation.repository.JooqInvitationRepository;
import io.github.lubosgarancovsky.aurora.business.invitation.usecase.DeleteInvitationUseCase;
import io.github.lubosgarancovsky.aurora.domain.invitation.command.DeleteInvitationCommand;
import org.springframework.stereotype.Service;

@Service
public class DeleteInvitationService implements DeleteInvitationUseCase {

    private final JooqInvitationRepository jooqInvitationRepository;

    public DeleteInvitationService(JooqInvitationRepository jooqInvitationRepository) {
        this.jooqInvitationRepository = jooqInvitationRepository;
    }

    @Override
    public Void execute(DeleteInvitationCommand deleteInvitationCommand) {
        this.jooqInvitationRepository.delete(deleteInvitationCommand);
        return null;
    }
}
