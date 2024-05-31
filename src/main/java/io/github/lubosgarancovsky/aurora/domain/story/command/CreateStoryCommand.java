package io.github.lubosgarancovsky.aurora.domain.story.command;

import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.UUID;

@Value.Immutable
public interface CreateStoryCommand extends Command {

    String name();
    String description();
    UUID typeId();
    Optional<UUID> assigneeId();
    Boolean inBoard();
    UUID userId();
    ProjectEntity project();
}
