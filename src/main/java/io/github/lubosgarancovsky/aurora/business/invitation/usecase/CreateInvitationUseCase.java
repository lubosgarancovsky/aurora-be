package io.github.lubosgarancovsky.aurora.business.invitation.usecase;

import io.github.lubosgarancovsky.aurora.domain.invitation.command.CreateInvitationCommand;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.port.UseCaseCommand;

public interface CreateInvitationUseCase extends UseCaseCommand<CreateInvitationCommand, EntityCreatedResponse> {
}
