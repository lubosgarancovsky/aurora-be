package io.github.lubosgarancovsky.aurora.business.invitation.repository.mapper;

import io.github.lubosgarancovsky.aurora.domain.invitation.entity.ImmutableInvitationEntity;
import io.github.lubosgarancovsky.aurora.domain.invitation.entity.ImmutableListOfInvitations;
import io.github.lubosgarancovsky.aurora.domain.invitation.entity.InvitationEntity;
import io.github.lubosgarancovsky.aurora.domain.invitation.entity.ListOfInvitations;
import io.github.lubosgarancovsky.aurora.domain.invitation.query.InvitationListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutablePublicUserResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.TriFunction;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Table;

import java.util.stream.Stream;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Teams.TEAMS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Projects.PROJECTS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Requests.REQUESTS;

public final class JooqInvitationRepositoryMapper {


    public static RecordMapper<Record, InvitationEntity> baseInvitationRecordMapper() {
        return record -> {

            Table<?> senderAlias = PARTNERS.as("sender");
            Table<?> recipientAlias = PARTNERS.as("recipient");

            final var senderPicture = record.get(senderAlias.field("picture"));
            final var recipientPicture = record.get(recipientAlias.field("picture"));

            PublicUserResponse sender = ImmutablePublicUserResponse.builder()
                    .id(record.get(senderAlias.field("id")).toString())
                    .name(record.get(senderAlias.field("name")).toString())
                    .email(record.get(senderAlias.field("email")).toString())
                    .color(record.get(senderAlias.field("color")).toString())
                    .picture(senderPicture == null ? null : senderPicture.toString())
                    .build();

            PublicUserResponse recipient = ImmutablePublicUserResponse.builder()
                    .id(record.get(recipientAlias.field("id")).toString())
                    .name(record.get(recipientAlias.field("name")).toString())
                    .email(record.get(recipientAlias.field("email")).toString())
                    .color(record.get(recipientAlias.field("color")).toString())
                    .picture(recipientPicture == null ? null : recipientPicture.toString())
                    .build();

            return ImmutableInvitationEntity.builder()
                    .id(record.get(REQUESTS.ID))
                    .createdAt(record.get(REQUESTS.CREATED_AT))
                    .teamId(record.get(TEAMS.ID))
                    .projectId(record.get(PROJECTS.ID))
                    .projectName(record.get(PROJECTS.NAME))
                    .sender(sender)
                    .recipient(recipient)
                    .build();

        };
    }

    public static final TriFunction<Stream<Record>, Integer, InvitationListingQuery, ListOfInvitations>
            listingMapper =
            (records, totalCount, query) ->
                    ImmutableListOfInvitations.builder()
                            .page(query.page())
                            .pageSize(query.pageSize().value())
                            .totalCount(totalCount)
                            .addAllItems(records.map(baseInvitationRecordMapper()::map).toList())
                            .build();
}
