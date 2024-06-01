package io.github.lubosgarancovsky.aurora.business.story.usecase;

import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryDetailQuery;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface DetailStoryUseCase extends UseCaseQuery<StoryDetailQuery, StoryEntity> {
}
