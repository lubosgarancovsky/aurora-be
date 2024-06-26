package io.github.lubosgarancovsky.aurora.domain.story.command;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.StoryRequest;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.SubstoryRequest;

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
                .stateId(UUID.fromString(request.stateId()))
                .assigneeId(assigneeId)
                .inBoard(request.inBoard())
                .project(project)
                .userId(UUID.fromString(userId))
                .build();
    }

    public static CreateSubstoryCommand createSubstoryCommand(SubstoryRequest request, String userId, String storyId) {
        return ImmutableCreateSubstoryCommand.builder()
                .name(request.name())
                .description(request.description())
                .typeId(UUID.fromString(request.typeId()))
                .stateId(UUID.fromString(request.stateId()))
                .assigneeId(Optional.ofNullable(request.assigneeId()).map(UUID::fromString))
                .parentId(UUID.fromString(storyId))
                .userId(UUID.fromString(userId))
                .build();
    }

    public static UpdateStoryCommand updateStoryCommand(String storyId, StoryRequest request) {
        return ImmutableUpdateStoryCommand.builder()
                .name(request.name())
                .description(request.description())
                .typeId(UUID.fromString(request.typeId()))
                .stateId(UUID.fromString(request.stateId()))
                .assigneeId(Optional.ofNullable(request.assigneeId()).map(UUID::fromString))
                .inBoard(request.inBoard())
                .storyId(UUID.fromString(storyId))
                .build();
    }

    public static UpdateSubstoryCommand updateSubstoryCommand(SubstoryRequest request, String storyId, String substoryId) {
        return ImmutableUpdateSubstoryCommand.builder()
                .name(request.name())
                .description(request.description())
                .typeId(UUID.fromString(request.typeId()))
                .stateId(UUID.fromString(request.stateId()))
                .assigneeId(Optional.ofNullable(request.assigneeId()).map(UUID::fromString))
                .storyId(UUID.fromString(storyId))
                .substoryId(UUID.fromString(substoryId))
                .build();
    }
}
