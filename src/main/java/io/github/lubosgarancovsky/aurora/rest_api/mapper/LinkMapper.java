package io.github.lubosgarancovsky.aurora.rest_api.mapper;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ImmutableProjectLinkEntity;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectLinkEntity;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.LinksRecord;
import org.jooq.Result;

import java.util.List;

public class LinkMapper {

    public static ProjectLinkEntity map(LinksRecord record) {
        return ImmutableProjectLinkEntity.builder()
                .id(record.getId())
                .projectId(record.getProjectId())
                .name(record.getName())
                .url(record.getUrl())
                .build();
    }

    public static List<ProjectLinkEntity> map(Result<LinksRecord> linksRecords) {
        return linksRecords.stream().map(LinkMapper::map).toList();
    }
}
