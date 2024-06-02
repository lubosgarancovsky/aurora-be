package io.github.lubosgarancovsky.aurora.business.story.service;

import io.github.lubosgarancovsky.aurora.business.story.repository.JooqSubstoryRepository;
import io.github.lubosgarancovsky.aurora.business.story.usecase.SubstoryDetailUseCase;
import io.github.lubosgarancovsky.aurora.domain.story.entity.SubstoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.query.SubstoryDetailQuery;
import org.springframework.stereotype.Service;

@Service
public class SubstoryDetailService implements SubstoryDetailUseCase {

    private final JooqSubstoryRepository jooqStoryRepository;

    public SubstoryDetailService(JooqSubstoryRepository jooqStoryRepository) {
        this.jooqStoryRepository = jooqStoryRepository;
    }

    @Override
    public SubstoryEntity execute(SubstoryDetailQuery query) {
        return this.jooqStoryRepository.detail(query);
    }
}
