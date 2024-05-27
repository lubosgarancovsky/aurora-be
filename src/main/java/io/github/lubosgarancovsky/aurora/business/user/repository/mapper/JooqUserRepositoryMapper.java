package io.github.lubosgarancovsky.aurora.business.user.repository.mapper;

import java.util.stream.Stream;

import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.TriFunction;
import org.jooq.Record;
import org.jooq.RecordMapper;

import io.github.lubosgarancovsky.aurora.domain.user.entity.ImmutableListOfUsers;
import io.github.lubosgarancovsky.aurora.domain.user.entity.ImmutableUserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.entity.ListOfUsers;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.UserListingQuery;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.PartnersRecord;

public final class JooqUserRepositoryMapper {

    public static RecordMapper<Record, UserEntity> baseUserRecordMapper() {
        return record -> {
            final PartnersRecord userRecord = record.into(PartnersRecord.class);

            return ImmutableUserEntity.builder()
                    .id(userRecord.getId())
                    .name(userRecord.getName())
                    .createdAt(userRecord.getCreatedAt())
                    .updatedAt(userRecord.getUpdatedAt())
                    .email(userRecord.getEmail())
                    .password("")
                    .color(userRecord.getColor())
                    .picture(userRecord.getPicture())
                    .build();

        };
    }

    public static final TriFunction<Stream<Record>, Integer, UserListingQuery, ListOfUsers>
        listingMapper =
            (records, totalCount, query) ->
                ImmutableListOfUsers.builder()
                        .page(query.page())
                        .pageSize(query.pageSize().value())
                        .totalCount(totalCount)
                        .addAllItems(records.map(baseUserRecordMapper()::map).toList())
                        .build();
}
