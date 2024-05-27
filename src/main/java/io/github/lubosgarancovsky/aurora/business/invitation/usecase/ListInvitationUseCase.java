package io.github.lubosgarancovsky.aurora.business.invitation.usecase;

import io.github.lubosgarancovsky.aurora.domain.invitation.entity.ListOfInvitations;
import io.github.lubosgarancovsky.aurora.domain.invitation.query.InvitationListingQuery;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface ListInvitationUseCase extends UseCaseQuery<InvitationListingQuery, ListOfInvitations> {
}
