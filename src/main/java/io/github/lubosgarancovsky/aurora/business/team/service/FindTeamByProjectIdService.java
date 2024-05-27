package io.github.lubosgarancovsky.aurora.business.team.service;

import io.github.lubosgarancovsky.aurora.business.team.repository.JooqTeamRepository;
import io.github.lubosgarancovsky.aurora.business.team.usecase.FindTeamByProjectIdUseCase;
import io.github.lubosgarancovsky.aurora.domain.team.command.FindTeamByProjectIdCommand;
import io.github.lubosgarancovsky.aurora.domain.team.entity.TeamEntity;
import org.springframework.stereotype.Service;

@Service
public class FindTeamByProjectIdService implements FindTeamByProjectIdUseCase {

    private final JooqTeamRepository jooqTeamRepository;

    public FindTeamByProjectIdService(JooqTeamRepository jooqTeamRepository) {
        this.jooqTeamRepository = jooqTeamRepository;
    }

    @Override
    public TeamEntity execute(FindTeamByProjectIdCommand command) {
        return this.jooqTeamRepository.findByProjectId(command);
    }
}
