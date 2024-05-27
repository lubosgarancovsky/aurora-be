package io.github.lubosgarancovsky.aurora.domain.user.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface DeleteUserCommand extends Command {
    UUID id();
}
