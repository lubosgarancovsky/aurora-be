package io.github.lubosgarancovsky.aurora.domain.invitation.query;

import io.github.lubosgarancovsky.aurora.domain.listing.InvitationsListingAttribute;
import io.github.lubosgarancovsky.domain.listing.ListingQuery;
import io.github.lubosgarancovsky.domain.marker.Query;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface InvitationListingQuery extends ListingQuery<InvitationsListingAttribute>, Query {

    UUID userId();
}
