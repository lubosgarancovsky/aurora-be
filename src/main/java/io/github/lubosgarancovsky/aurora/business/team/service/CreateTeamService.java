package io.github.lubosgarancovsky.aurora.business.team.service;

import io.github.lubosgarancovsky.aurora.business.team.repository.JooqTeamRepository;
import io.github.lubosgarancovsky.aurora.business.team.usecase.CreateTeamUseCase;
import io.github.lubosgarancovsky.aurora.domain.team.command.CreateTeamCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import org.springframework.stereotype.Service;

import static io.github.lubosgarancovsky.aurora.rest_api.error.TeamErrorCode.TEAM_ALREADY_EXISTS_ON_PROJECT;

@Service
public class CreateTeamService implements CreateTeamUseCase {

    public final JooqTeamRepository jooqTeamRepository;

    public CreateTeamService(JooqTeamRepository jooqTeamRepository) {
        this.jooqTeamRepository = jooqTeamRepository;
    }

    @Override
    public EntityCreatedResponse execute(CreateTeamCommand createTeamCommand) {
        if(this.jooqTeamRepository.existsByProjectId(createTeamCommand.projectId())) {
            throw TEAM_ALREADY_EXISTS_ON_PROJECT.createError(createTeamCommand.projectId()).convertToException();
        }

        return this.jooqTeamRepository.insert(createTeamCommand);
    }
}
