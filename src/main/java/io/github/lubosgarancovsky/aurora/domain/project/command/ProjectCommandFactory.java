package io.github.lubosgarancovsky.aurora.domain.project.command;

import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ProjectRequest;

import java.util.UUID;

public class ProjectCommandFactory {

    public static CreateProjectCommand createCreateCommand(ProjectRequest request, UUID userId) {
        return ImmutableCreateProjectCommand.builder()
                .name(request.name())
                .description(request.description())
                .code(request.code())
                .createdBy(userId)
                .build();
    }

    public static DeleteProjectCommand createDeleteCommand(UUID id, UUID userId) {
        return ImmutableDeleteProjectCommand.builder()
                .id(id)
                .userId(userId)
                .build();
    }
}
