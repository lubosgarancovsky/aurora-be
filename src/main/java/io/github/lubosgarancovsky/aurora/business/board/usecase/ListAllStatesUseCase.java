package io.github.lubosgarancovsky.aurora.business.board.usecase;

import io.github.lubosgarancovsky.aurora.domain.board.entity.StoryStateEntity;
import io.github.lubosgarancovsky.domain.marker.Query;
import io.github.lubosgarancovsky.port.UseCaseQuery;

import java.util.List;

public interface ListAllStatesUseCase extends UseCaseQuery<Query, List<StoryStateEntity>> {
}
