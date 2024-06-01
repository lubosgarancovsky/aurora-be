package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqStoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.UpdateStoryUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.command.UpdateStoryCommand;
import org.springframework.stereotype.Service;

@Service
public class UpdateStoryService implements UpdateStoryUseCase {

    private final JooqStoryRepository jooqStoryRepository;

    public UpdateStoryService(JooqStoryRepository jooqStoryRepository) {
        this.jooqStoryRepository = jooqStoryRepository;
    }
    @Override
    public Void execute(UpdateStoryCommand command) {
        this.jooqStoryRepository.update(command);
        return null;
    }
}
