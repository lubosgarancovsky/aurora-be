package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqStoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.CreateStoryUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.command.CreateStoryCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateStoryService implements CreateStoryUseCase {

    private final JooqStoryRepository jooqStoryRepository;

    public CreateStoryService(JooqStoryRepository jooqStoryRepository) {
        this.jooqStoryRepository = jooqStoryRepository;
    }

    @Override
    public EntityCreatedResponse execute(CreateStoryCommand command) {
        return this.jooqStoryRepository.insert(command);
    }
}
