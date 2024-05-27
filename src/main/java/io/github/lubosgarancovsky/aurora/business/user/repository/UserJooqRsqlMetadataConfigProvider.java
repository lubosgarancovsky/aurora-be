package io.github.lubosgarancovsky.aurora.business.user.repository;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;

import io.github.lubosgarancovsky.aurora.domain.listing.UsersListingAttribute;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadata;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadataConfigProvider;

public class UserJooqRsqlMetadataConfigProvider implements JooqRsqlMetadataConfigProvider {

    @Override
    public void configure(JooqRsqlMetadata metadata) {
        metadata
                .addColumnInfo(UsersListingAttribute.NAME.apiName(), PARTNERS.NAME)
                .addColumnInfo(UsersListingAttribute.EMAIL.apiName(), PARTNERS.EMAIL)
                .addColumnInfo(UsersListingAttribute.CREATED_AT.apiName(), PARTNERS.CREATED_AT)
                .addColumnInfo(UsersListingAttribute.UPDATED_AT.apiName(), PARTNERS.UPDATED_AT);

    }
    
}
