package io.github.lubosgarancovsky.aurora.business.user.repository;


import io.github.lubosgarancovsky.aurora.business.JooqRepository;
import io.github.lubosgarancovsky.aurora.domain.user.command.DeleteUserCommand;
import io.github.lubosgarancovsky.aurora.domain.user.command.RegisterUserCommand;
import io.github.lubosgarancovsky.aurora.domain.user.entity.ImmutableUserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.entity.ListOfUsers;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.FindUserByIdQuery;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.UserListingQuery;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.PartnersRecord;
import io.github.lubosgarancovsky.persistance.jooq.handler.JooqPaginatedSelectQueryHandler;
import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.PaginatedResultMapper;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static io.github.lubosgarancovsky.aurora.business.user.repository.mapper.JooqUserRepositoryMapper.listingMapper;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;
import static io.github.lubosgarancovsky.aurora.rest_api.error.UserErrorCode.USER_NOT_FOUND;



@Repository
public class JooqUserRepository extends JooqRepository {

    public JooqUserRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public UserEntity findOne(String email) {
        PartnersRecord record = dslContext
                .selectFrom(PARTNERS)
                .where(PARTNERS.EMAIL.eq(email))
                .fetchOne();

        if(record == null) {
            throw USER_NOT_FOUND.createError(email).convertToException();
        }


        return this.map(record);

    }

    public UserEntity findOne(FindUserByIdQuery query) {
        PartnersRecord record = dslContext
                .selectFrom(PARTNERS)
                .where(PARTNERS.ID.eq(query.id()))
                .fetchOne();

        if(record == null) {
            throw USER_NOT_FOUND.createError(query.id()).convertToException();
        }


        return this.map(record);
    }

    public ListOfUsers list(UserListingQuery query) {
        final var select = dslContext
                .select(
                        PARTNERS.ID,
                        PARTNERS.NAME,
                        PARTNERS.EMAIL,
                        PARTNERS.COLOR,
                        PARTNERS.PICTURE,
                        PARTNERS.PASSWORD,
                        PARTNERS.CREATED_AT,
                        PARTNERS.UPDATED_AT
                )
                .from(PARTNERS);

        final JooqPaginatedSelectQueryHandler<UserListingQuery, ListOfUsers>
                paginatedListingHandler =
                    new JooqPaginatedSelectQueryHandler<>(
                        query, new UserJooqRsqlMetadataConfigProvider());
        
        final PaginatedResultMapper<UserListingQuery, ListOfUsers> paginated =
                paginatedListingHandler.handle(select.getQuery());
        
        return paginated.map(listingMapper);


    }

    public UserEntity insert(RegisterUserCommand command) {
        final PartnersRecord record = dslContext
                .insertInto(PARTNERS)
                .set(PARTNERS.NAME, command.name())
                .set(PARTNERS.EMAIL, command.email())
                .set(PARTNERS.PASSWORD, command.password())
                .set(PARTNERS.COLOR, command.color())
                .returning()
                .fetchOne();

        if(record == null) {
            throw USER_NOT_FOUND.createError(command.email()).convertToException();
        }

        return map(record);
    }

    public Boolean existsByEmail(String email) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectFrom(PARTNERS)
                                .where(PARTNERS.EMAIL.eq(email))
                );
    }

    public void delete(DeleteUserCommand command) {
        dslContext.deleteFrom(PARTNERS).where(PARTNERS.ID.eq(command.id())).execute();
    }

    private UserEntity map(PartnersRecord record) {
        return ImmutableUserEntity.builder()
                .id(record.get(PARTNERS.ID))
                .name(record.get(PARTNERS.NAME))
                .email(record.get(PARTNERS.EMAIL))
                .password(record.get(PARTNERS.PASSWORD))
                .color(record.get(PARTNERS.COLOR))
                .picture(record.get(PARTNERS.PICTURE))
                .createdAt(record.get(PARTNERS.CREATED_AT))
                .updatedAt(record.get(PARTNERS.UPDATED_AT))
                .build();
    }
}
