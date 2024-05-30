package io.github.lubosgarancovsky.aurora.business.board.service;

import io.github.lubosgarancovsky.aurora.business.board.repository.JooqBoardRepository;
import io.github.lubosgarancovsky.aurora.business.board.usecase.ListAllStatesUseCase;
import io.github.lubosgarancovsky.aurora.domain.board.entity.StoryStateEntity;
import io.github.lubosgarancovsky.domain.marker.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllStatesService implements ListAllStatesUseCase {

    private final JooqBoardRepository jooqBoardRepository;

    public ListAllStatesService(JooqBoardRepository jooqBoardRepository) {
        this.jooqBoardRepository = jooqBoardRepository;
    }

    @Override
    public List<StoryStateEntity> execute(Query query) {
        return (List<StoryStateEntity>) this.jooqBoardRepository.listAllStates();
    }
}
