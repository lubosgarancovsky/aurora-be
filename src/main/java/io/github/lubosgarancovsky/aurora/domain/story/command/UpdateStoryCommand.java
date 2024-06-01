package io.github.lubosgarancovsky.aurora.domain.story.command;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.UUID;

@Value.Immutable
public interface UpdateStoryCommand extends Command {

    String name();
    String description();
    UUID typeId();
    UUID stateId();
    Optional<UUID> assigneeId();
    Boolean inBoard();
    UUID storyId();
}
