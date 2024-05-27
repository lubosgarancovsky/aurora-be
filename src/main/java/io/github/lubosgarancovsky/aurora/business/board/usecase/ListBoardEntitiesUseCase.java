package io.github.lubosgarancovsky.aurora.business.board.usecase;

import io.github.lubosgarancovsky.aurora.domain.board.query.BoardListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.BoardResponse;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface ListBoardEntitiesUseCase extends UseCaseQuery<BoardListingQuery, BoardResponse> {
}
