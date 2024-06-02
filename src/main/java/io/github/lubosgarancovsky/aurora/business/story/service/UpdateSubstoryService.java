package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqSubstoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.UpdateSubstoryUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.command.UpdateSubstoryCommand;
import io.github.lubosgarancovsky.aurora.domain.story.entity.SubstoryEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateSubstoryService implements UpdateSubstoryUseCase {

    private final JooqSubstoryRepository jooqStoryRepository;

    public UpdateSubstoryService(JooqSubstoryRepository jooqStoryRepository) {
        this.jooqStoryRepository = jooqStoryRepository;
    }

    @Override
    public SubstoryEntity execute(UpdateSubstoryCommand command) {
        return this.jooqStoryRepository.update(command);
    }

}
