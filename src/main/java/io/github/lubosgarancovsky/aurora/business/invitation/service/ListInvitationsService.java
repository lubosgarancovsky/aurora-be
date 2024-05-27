package io.github.lubosgarancovsky.aurora.business.invitation.service;

import io.github.lubosgarancovsky.aurora.business.invitation.repository.JooqInvitationRepository;
import io.github.lubosgarancovsky.aurora.business.invitation.usecase.ListInvitationUseCase;
import io.github.lubosgarancovsky.aurora.domain.invitation.entity.ListOfInvitations;
import io.github.lubosgarancovsky.aurora.domain.invitation.query.InvitationListingQuery;
import org.springframework.stereotype.Service;

@Service
public class ListInvitationsService implements ListInvitationUseCase {

    private final JooqInvitationRepository jooqInvitationRepository;

    public ListInvitationsService(JooqInvitationRepository jooqInvitationRepository) {
        this.jooqInvitationRepository = jooqInvitationRepository;
    }

    @Override
    public ListOfInvitations execute(InvitationListingQuery invitationListingQuery) {
        return this.jooqInvitationRepository.list(invitationListingQuery);
    }
}
