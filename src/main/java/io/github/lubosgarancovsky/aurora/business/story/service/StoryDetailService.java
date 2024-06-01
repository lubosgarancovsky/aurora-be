package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqStoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.DetailStoryUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryDetailQuery;
import org.springframework.stereotype.Service;

@Service
public class StoryDetailService implements DetailStoryUseCase {
    private final JooqStoryRepository jooqStoryRepository;

    public StoryDetailService(JooqStoryRepository jooqStoryRepository) {
        this.jooqStoryRepository = jooqStoryRepository;
    }

    @Override
    public StoryEntity execute(StoryDetailQuery query) {
        return this.jooqStoryRepository.detail(query);
    }
}
