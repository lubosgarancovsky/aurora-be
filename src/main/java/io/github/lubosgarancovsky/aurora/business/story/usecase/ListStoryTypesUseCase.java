package io.github.lubosgarancovsky.aurora.business.story.usecase;


import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryTypeEntity;

import java.util.List;

public interface ListStoryTypesUseCase {
    List<StoryTypeEntity> execute();
}
