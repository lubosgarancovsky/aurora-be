package io.github.lubosgarancovsky.aurora.business.invitation.service;

import io.github.lubosgarancovsky.aurora.business.invitation.repository.JooqInvitationRepository;
import io.github.lubosgarancovsky.aurora.business.invitation.usecase.AcceptInvitationUseCase;
import io.github.lubosgarancovsky.aurora.domain.invitation.command.AcceptInvitationCommand;
import org.springframework.stereotype.Service;

@Service
public class AcceptInvitationService implements AcceptInvitationUseCase {

    private final JooqInvitationRepository jooqInvitationRepository;

    public AcceptInvitationService(JooqInvitationRepository jooqInvitationRepository) {
        this.jooqInvitationRepository = jooqInvitationRepository;
    }

    @Override
    public Void execute(AcceptInvitationCommand acceptInvitationCommand) {
        this.jooqInvitationRepository.accept(acceptInvitationCommand);
        return null;
    }
}
