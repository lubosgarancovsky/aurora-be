package io.github.lubosgarancovsky.aurora.domain.project.query;

import io.github.lubosgarancovsky.aurora.domain.listing.ProjectsListingAttribute;
import io.github.lubosgarancovsky.domain.listing.ListingQuery;

import io.github.lubosgarancovsky.domain.marker.Query;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface ProjectListingQuery extends ListingQuery<ProjectsListingAttribute>, Query {

    UUID userId();
}
