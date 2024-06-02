package io.github.lubosgarancovsky.aurora.business.story.usecase;

import io.github.lubosgarancovsky.aurora.domain.story.entity.SubstoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.query.SubstoryDetailQuery;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface SubstoryDetailUseCase extends UseCaseQuery<SubstoryDetailQuery, SubstoryEntity> {
}
