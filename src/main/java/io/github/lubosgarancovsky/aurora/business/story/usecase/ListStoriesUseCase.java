package io.github.lubosgarancovsky.aurora.business.story.usecase;

import io.github.lubosgarancovsky.aurora.domain.story.query.StoryListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.ListOfStories;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface ListStoriesUseCase extends UseCaseQuery<StoryListingQuery, ListOfStories> {
}
