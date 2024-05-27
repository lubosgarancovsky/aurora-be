package io.github.lubosgarancovsky.aurora.business.board.service;

import io.github.lubosgarancovsky.aurora.business.board.repository.JooqBoardRepository;
import io.github.lubosgarancovsky.aurora.business.board.usecase.ListBoardEntitiesUseCase;
import io.github.lubosgarancovsky.aurora.domain.board.query.BoardListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.BoardResponse;
import org.springframework.stereotype.Service;

@Service
public class ListBoardEntitiesService implements ListBoardEntitiesUseCase {

    private final JooqBoardRepository jooqBoardRepository;

    public ListBoardEntitiesService(JooqBoardRepository jooqBoardRepository) {
        this.jooqBoardRepository = jooqBoardRepository;
    }

    @Override
    public BoardResponse execute(BoardListingQuery query) {
        return this.jooqBoardRepository.list(query);
    }
}
