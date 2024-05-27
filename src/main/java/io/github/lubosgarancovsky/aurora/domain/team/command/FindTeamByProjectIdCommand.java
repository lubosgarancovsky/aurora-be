package io.github.lubosgarancovsky.aurora.domain.team.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface FindTeamByProjectIdCommand extends Command {

    UUID projectId();
}
