package io.github.lubosgarancovsky.aurora.domain.story.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.UUID;

@Value.Immutable
public interface UpdateSubstoryCommand extends Command {

    String name();
    String description();
    UUID typeId();
    UUID stateId();
    Optional<UUID> assigneeId();
    UUID storyId();
    UUID substoryId();
}
