package io.github.lubosgarancovsky.aurora.domain.user.query.users;

import org.immutables.value.Value;

import io.github.lubosgarancovsky.aurora.domain.listing.UsersListingAttribute;
import io.github.lubosgarancovsky.domain.listing.ListingQuery;
import io.github.lubosgarancovsky.domain.marker.Query;

@Value.Immutable
public interface UserListingQuery extends ListingQuery<UsersListingAttribute>, Query {
    
    
}
