package io.github.lubosgarancovsky.aurora.rest_api.api_dto.story;

import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryEntity;
import io.github.lubosgarancovsky.domain.listing.PaginatedResult;
import org.immutables.value.Value;

@Value.Immutable
public interface ListOfStories extends PaginatedResult<StoryEntity> {
}
