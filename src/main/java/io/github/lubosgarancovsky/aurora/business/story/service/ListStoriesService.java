package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqStoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.ListStoriesUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.ListOfStories;
import org.springframework.stereotype.Service;

@Service
public class ListStoriesService implements ListStoriesUseCase {

    private final JooqStoryRepository jooqStoryRepository;

    public ListStoriesService(JooqStoryRepository storyRepository) {
        this.jooqStoryRepository = storyRepository;
    }

    @Override
    public ListOfStories execute(StoryListingQuery query) {
        return this.jooqStoryRepository.list(query);
    }
}
