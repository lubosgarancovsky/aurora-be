package io.github.lubosgarancovsky.aurora.business.board.service;

import io.github.lubosgarancovsky.aurora.business.board.repository.JooqBoardRepository;
import io.github.lubosgarancovsky.aurora.business.board.usecase.UpdateBoardUseCase;
import io.github.lubosgarancovsky.aurora.domain.board.command.UpdateBoardCommand;
import org.springframework.stereotype.Service;

@Service
public class UpdateBoardService implements UpdateBoardUseCase {

    private final JooqBoardRepository jooqBoardRepository;

    public UpdateBoardService(JooqBoardRepository jooqBoardRepository) {
        this.jooqBoardRepository = jooqBoardRepository;
    }

    @Override
    public Void execute(UpdateBoardCommand updateBoardCommand) {
        this.jooqBoardRepository.update(updateBoardCommand);
        return null;
    }
}
