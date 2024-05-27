package io.github.lubosgarancovsky.aurora.business.user.service;

import io.github.lubosgarancovsky.aurora.business.user.repository.JooqUserRepository;
import io.github.lubosgarancovsky.aurora.business.user.usecase.DeleteUserUseCase;
import io.github.lubosgarancovsky.aurora.domain.user.command.DeleteUserCommand;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService implements DeleteUserUseCase {

    public final JooqUserRepository jooqUserRepository;

    public DeleteUserService(JooqUserRepository jooqUserRepository) {
        this.jooqUserRepository = jooqUserRepository;
    }

    @Override
    public String execute(DeleteUserCommand deleteUserCommand) {
        jooqUserRepository.delete(deleteUserCommand);
        return "OK";
    }
}
