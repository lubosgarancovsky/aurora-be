package io.github.lubosgarancovsky.aurora.business.invitation.service;

import io.github.lubosgarancovsky.aurora.business.invitation.repository.JooqInvitationRepository;
import io.github.lubosgarancovsky.aurora.business.invitation.usecase.CreateInvitationUseCase;
import io.github.lubosgarancovsky.aurora.domain.invitation.command.CreateInvitationCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateInvitationService implements CreateInvitationUseCase {

    private final JooqInvitationRepository jooqInvitationRepository;

    public CreateInvitationService(JooqInvitationRepository jooqInvitationRepository) {
        this.jooqInvitationRepository = jooqInvitationRepository;
    }

    @Override
    public EntityCreatedResponse execute(CreateInvitationCommand createInvitationCommand) {
        return this.jooqInvitationRepository.insert(createInvitationCommand);
    }
}
