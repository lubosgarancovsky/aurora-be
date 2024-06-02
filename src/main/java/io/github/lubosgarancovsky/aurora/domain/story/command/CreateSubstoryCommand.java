package io.github.lubosgarancovsky.aurora.domain.story.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.UUID;

@Value.Immutable
public interface CreateSubstoryCommand extends Command {

    UUID userId();
    UUID parentId();
    String name();
    String description();
    UUID stateId();
    UUID typeId();
    Optional<UUID> assigneeId();

}
