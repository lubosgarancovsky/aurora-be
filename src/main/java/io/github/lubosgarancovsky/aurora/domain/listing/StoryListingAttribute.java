package io.github.lubosgarancovsky.aurora.domain.listing;

import io.github.lubosgarancovsky.domain.shared.listing.ListingAttribute;

public enum StoryListingAttribute implements ListingAttribute {
    NAME("name", true, true),
    CODE("code", false, true),
    PROJECT_ID("projectId", false, true),
    CREATED_AT("createdAt", true, true),
    CREATED_BY("createdBy", false, true),
    IN_BOARD("inBoard", false, true),
    STATE_ID("stateId", false, true),
    TYPE_ID("typeId", false, true),
    ASSIGNED_TO("assignedTo", false, true);

    StoryListingAttribute(String apiName, boolean forSorting, boolean forFiltering) {
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
