package io.github.lubosgarancovsky.aurora.domain.board.command;

import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface UpdateBoardCommand extends Command {

    UUID projectId();
    UUID userId();
    List<UUID> items();
}
