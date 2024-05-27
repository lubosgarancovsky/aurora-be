package io.github.lubosgarancovsky.aurora.business.invitation.repository;

import io.github.lubosgarancovsky.aurora.domain.listing.InvitationsListingAttribute;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadata;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadataConfigProvider;
import org.jooq.Table;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Projects.PROJECTS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Requests.REQUESTS;

public class InvitationJooqRsqlMetadataConfigProvider implements JooqRsqlMetadataConfigProvider {

    @Override
    public void configure(JooqRsqlMetadata metadata) {
        Table<?> senderAlias = PARTNERS.as("sender");
        Table<?> recipientAlias = PARTNERS.as("recipient");

        metadata
                .addColumnInfo(InvitationsListingAttribute.PROJECT_ID.apiName(), PROJECTS.ID)
                .addColumnInfo(InvitationsListingAttribute.SENDER_ID.apiName(), senderAlias.field("id"))
                .addColumnInfo(InvitationsListingAttribute.RECIPIENT_ID.apiName(), recipientAlias.field("id"))
                .addColumnInfo(InvitationsListingAttribute.CREATED_AT.apiName(), REQUESTS.CREATED_AT);

    }
}
