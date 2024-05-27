package io.github.lubosgarancovsky.aurora.rest_api.mapper;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ImmutableProjectEntity;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ListOfProjects;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ImmutableProjectListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ImmutableProjectResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ProjectListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ProjectResponse;
import org.jooq.Record;

public class ProjectMapper {

    public static ProjectResponse map(ProjectEntity project) {
        return ImmutableProjectResponse.builder()
                .id(project.id().toString())
                .name(project.name())
                .code(project.code())
                .description(project.description())
                .createdAt(project.createdAt())
                .createdBy(project.createdBy())
            .build();
    }

    public static ProjectListResponse map(ListOfProjects list) {
        return ImmutableProjectListResponse.builder()
            .totalCount(list.totalCount())
            .pageSize(list.pageSize())
            .page(list.page())
            .addAllItems(list.items().stream().map(ProjectMapper::map).toList())
            .build();
    }
}
