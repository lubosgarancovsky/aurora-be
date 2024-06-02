package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqSubstoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.CreateSubstoryUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.command.CreateSubstoryCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateSubstoryService implements CreateSubstoryUseCase {

    private final JooqSubstoryRepository jooqStoryRepository;

    public CreateSubstoryService(JooqSubstoryRepository jooqStoryRepository) {
        this.jooqStoryRepository = jooqStoryRepository;
    }

    @Override
    public EntityCreatedResponse execute(CreateSubstoryCommand command) {
        return this.jooqStoryRepository.insert(command);
    }
}
