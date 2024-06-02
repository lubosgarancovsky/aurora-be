package io.github.lubosgarancovsky.aurora.business.story.usecase;

import io.github.lubosgarancovsky.aurora.domain.story.command.UpdateSubstoryCommand;
import io.github.lubosgarancovsky.aurora.domain.story.entity.SubstoryEntity;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface UpdateSubstoryUseCase extends UseCaseCommand<UpdateSubstoryCommand, SubstoryEntity> {
}
