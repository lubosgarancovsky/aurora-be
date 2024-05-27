package io.github.lubosgarancovsky.aurora.domain.user.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

@Value.Immutable
public interface LoginUserCommand extends Command {

    String email();

    String password();
}
