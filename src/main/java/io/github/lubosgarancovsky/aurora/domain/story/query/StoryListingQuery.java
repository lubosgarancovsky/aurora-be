package io.github.lubosgarancovsky.aurora.domain.story.query;

import io.github.lubosgarancovsky.aurora.domain.listing.StoryListingAttribute;
import io.github.lubosgarancovsky.domain.listing.ListingQuery;
import io.github.lubosgarancovsky.domain.marker.Query;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface StoryListingQuery extends ListingQuery<StoryListingAttribute>, Query {
}
