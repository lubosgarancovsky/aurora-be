package io.github.lubosgarancovsky.aurora.business.project.repository.mapper;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ImmutableListOfProjects;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ImmutableProjectEntity;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ListOfProjects;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.aurora.domain.project.query.ProjectListingQuery;
import io.github.lubosgarancovsky.aurora.domain.user.entity.ImmutableUserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.rest_api.mapper.UserMapper;
import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.TriFunction;
import org.jooq.RecordMapper;
import org.jooq.Record;

import java.util.stream.Stream;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Projects.PROJECTS;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.Partners.PARTNERS;

public final class JooqProjectRepositoryMapper {

    public static RecordMapper<Record, ProjectEntity> baseProjectRecordMapper() {
        return record -> {

            UserEntity createdBy = ImmutableUserEntity.builder()
                    .id(record.get(PARTNERS.ID))
                    .name(record.get(PARTNERS.NAME))
                    .email(record.get(PARTNERS.EMAIL))
                    .color(record.get(PARTNERS.COLOR))
                    .picture(record.get(PARTNERS.PICTURE))
                    .password("")
                    .createdAt(record.get(PARTNERS.CREATED_AT))
                    .updatedAt(record.get(PARTNERS.UPDATED_AT))
                    .build();

            return ImmutableProjectEntity.builder()
                    .id(record.get(PROJECTS.ID))
                    .name(record.get(PROJECTS.NAME))
                    .description(record.get(PROJECTS.DESCRIPTION))
                    .code(record.get(PROJECTS.CODE))
                    .createdAt(record.get(PROJECTS.CREATED_AT))
                    .createdBy(UserMapper.map(createdBy))
                    .build();
        };
    }

    public static final TriFunction<Stream<Record>, Integer, ProjectListingQuery, ListOfProjects>
            listingMapper =
            (records, totalCount, query) ->
                    ImmutableListOfProjects.builder()
                            .page(query.page())
                            .pageSize(query.pageSize().value())
                            .totalCount(totalCount)
                            .addAllItems(records.map(baseProjectRecordMapper()::map).toList())
                            .build();
}
