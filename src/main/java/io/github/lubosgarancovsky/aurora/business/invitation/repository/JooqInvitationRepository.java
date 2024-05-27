package io.github.lubosgarancovsky.aurora.business.invitation.repository;

import io.github.lubosgarancovsky.aurora.business.JooqRepository;
import io.github.lubosgarancovsky.aurora.domain.invitation.command.AcceptInvitationCommand;
import io.github.lubosgarancovsky.aurora.domain.invitation.command.CreateInvitationCommand;
import io.github.lubosgarancovsky.aurora.domain.invitation.command.DeleteInvitationCommand;
import io.github.lubosgarancovsky.aurora.domain.invitation.entity.ListOfInvitations;
import io.github.lubosgarancovsky.aurora.domain.invitation.query.InvitationListingQuery;
import io.github.lubosgarancovsky.aurora.repository.model.tables.Partners;
import io.github.lubosgarancovsky.aurora.repository.model.tables.Projects;
import io.github.lubosgarancovsky.aurora.repository.model.tables.Requests;
import io.github.lubosgarancovsky.aurora.repository.model.tables.Teams;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.ImmutableEntityCreatedResponse;
import io.github.lubosgarancovsky.persistance.jooq.handler.JooqPaginatedSelectQueryHandler;
import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.PaginatedResultMapper;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import static io.github.lubosgarancovsky.aurora.business.invitation.repository.mapper.JooqInvitationRepositoryMapper.listingMapper;


import static io.github.lubosgarancovsky.aurora.repository.model.tables.Requests.REQUESTS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.TeamPartner.TEAM_PARTNER;
import static io.github.lubosgarancovsky.aurora.rest_api.error.InvitationErrorCode.*;

@Repository
public class JooqInvitationRepository extends JooqRepository {

    protected JooqInvitationRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public EntityCreatedResponse insert(CreateInvitationCommand command) {
        final var invitation = dslContext.insertInto(REQUESTS)
                .set(REQUESTS.TEAM_ID, command.teamId())
                .set(REQUESTS.RECIPIENT_ID, command.recipientId())
                .set(REQUESTS.SENDER_ID, command.senderId())
                .returning()
                .fetchOne();

        if(invitation == null) {
            throw new RuntimeException("Could not create invitation");
        }

        return ImmutableEntityCreatedResponse.builder()
                .id(invitation.getId().toString())
                .build();
    }

    public void delete(DeleteInvitationCommand command) {
        final var invitation = dslContext.selectFrom(REQUESTS).where(REQUESTS.ID.eq(command.invitationId())).fetchOne();

        if(invitation != null && (!invitation.get(REQUESTS.SENDER_ID).equals(command.userId()) && !invitation.get(REQUESTS.RECIPIENT_ID).equals(command.userId()))) {
            throw FORBIDDEN_FOR_USER.createError().convertToException();
        }

        dslContext.delete(REQUESTS)
                .where(REQUESTS.ID.eq(command.invitationId()))
                .execute();
    }

    public ListOfInvitations list(InvitationListingQuery query) {
        Requests r = Requests.REQUESTS;
        Partners s = Partners.PARTNERS.as("sender");
        Partners p = Partners.PARTNERS.as("recipient");
        Teams t = Teams.TEAMS;
        Projects pr = Projects.PROJECTS;

        final var select = dslContext
                .select(
                        r.ID,
                        r.CREATED_AT,
                        t.ID,
                        s.ID,
                        s.NAME,
                        s.EMAIL,
                        s.COLOR,
                        s.PICTURE,
                        p.ID,
                        p.NAME,
                        p.EMAIL,
                        p.COLOR,
                        p.PICTURE,
                        pr.ID,
                        pr.NAME
                )
                .from(r)
                .join(s).on(s.ID.eq(r.SENDER_ID))
                .join(p).on(p.ID.eq(r.RECIPIENT_ID))
                .join(t).on(t.ID.eq(r.TEAM_ID))
                .join(pr).on(pr.ID.eq(t.PROJECT_ID))
                .where(r.SENDER_ID.eq(query.userId()).or(r.RECIPIENT_ID.eq(query.userId())));

        final JooqPaginatedSelectQueryHandler<InvitationListingQuery, ListOfInvitations>
                paginatedListingHandler =
                new JooqPaginatedSelectQueryHandler<>(
                        query, new InvitationJooqRsqlMetadataConfigProvider());

        final PaginatedResultMapper<InvitationListingQuery, ListOfInvitations> paginated =
                paginatedListingHandler.handle(select.getQuery());

        return paginated.map(listingMapper);
    }

    public void accept(AcceptInvitationCommand command) {
        final var invitation = dslContext.selectFrom(REQUESTS).where(REQUESTS.ID.eq(command.invitationId())).fetchOne();

        if(invitation == null) {
            throw NOT_FOUND.createError(command.invitationId().toString()).convertToException();
        }

        if(!invitation.get(REQUESTS.RECIPIENT_ID).equals(command.userId())) {
            throw CANNOT_ACCEPT.createError(command.invitationId().toString()).convertToException();
        }

        dslContext.insertInto(TEAM_PARTNER)
                .set(TEAM_PARTNER.TEAM_ID, invitation.getTeamId())
                .set(TEAM_PARTNER.PARTNER_ID,invitation.getRecipientId())
                .execute();
    }
}
