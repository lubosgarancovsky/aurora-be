package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqStoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.ListStoryTypesUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryTypeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListStoryTypesService implements ListStoryTypesUseCase {

    private final JooqStoryRepository jooqStoryRepository;

    public ListStoryTypesService(JooqStoryRepository jooqStoryRepository) {
        this.jooqStoryRepository = jooqStoryRepository;
    }
    @Override
    public List<StoryTypeEntity> execute() {
        return this.jooqStoryRepository.listStoryTypes();
    }

}
