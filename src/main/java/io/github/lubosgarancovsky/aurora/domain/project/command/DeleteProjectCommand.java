package io.github.lubosgarancovsky.aurora.domain.project.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface DeleteProjectCommand extends Command {

    UUID id();

    UUID userId();
}
