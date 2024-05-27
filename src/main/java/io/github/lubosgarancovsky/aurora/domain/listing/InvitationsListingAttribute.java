package io.github.lubosgarancovsky.aurora.domain.listing;

import io.github.lubosgarancovsky.domain.shared.listing.ListingAttribute;

public enum InvitationsListingAttribute implements ListingAttribute {
    PROJECT_ID("projectId", false, true),
    SENDER_ID("senderId", false, true),
    RECIPIENT_ID("recipientId", false, true),

    CREATED_AT("createdAt", true, true);

    InvitationsListingAttribute(String apiName, boolean forSorting, boolean forFiltering) {
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
