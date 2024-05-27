package io.github.lubosgarancovsky.aurora.domain.listing;

import io.github.lubosgarancovsky.domain.shared.listing.ListingAttribute;

public enum ProjectsListingAttribute implements ListingAttribute {


    NAME("name", true, true),
    CREATED_BY("created_by", true, false);

    ProjectsListingAttribute(String apiName, boolean forSorting, boolean forFiltering) {
        this.apiName = apiName;
        this.forSorting = forSorting;
        this.forFiltering = forFiltering;
    }



    private final String apiName;
    private final boolean forSorting;
    private final boolean forFiltering;

    @Override
    public String apiName() {
        return apiName;
    }

    @Override
    public boolean isForSorting() {
        return forSorting;
    }

    @Override
    public boolean isForFiltering() {
        return forFiltering;
    }

}
