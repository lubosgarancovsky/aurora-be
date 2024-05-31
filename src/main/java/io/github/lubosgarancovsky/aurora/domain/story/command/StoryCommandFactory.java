package io.github.lubosgarancovsky.aurora.domain.story.command;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.StoryRequest;

import java.util.Optional;
import java.util.UUID;

public class StoryCommandFactory {

    public static CreateStoryCommand createStoryCommand(StoryRequest request, ProjectEntity project, String userId) {
        Optional<UUID> assigneeId = Optional.empty();

        if(request.assigneeId() != null) {
            assigneeId = Optional.of(UUID.fromString(request.assigneeId()));
        }

        return ImmutableCreateStoryCommand.builder()
                .name(request.name())
                .description(request.description())
                .typeId(UUID.fromString(request.typeId()))
                .assigneeId(assigneeId)
                .inBoard(request.inBoard())
                .project(project)
                .userId(UUID.fromString(userId))
                .build();
    }
}
