package io.github.lubosgarancovsky.aurora.domain.board.command;

import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.BoardRequest;

import java.util.UUID;

public final class BoardCommandFactory {

    public static UpdateBoardCommand createUpdateCommand(BoardRequest request, String id, String userId) {
        return ImmutableUpdateBoardCommand.builder()
                .userId(UUID.fromString(userId))
                .projectId(UUID.fromString(id))
                .items(request.items().stream().map(UUID::fromString).toList())
                .build();
    }
}
