package io.github.lubosgarancovsky.aurora.domain.user.command;

import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.LoginRequest;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.RegisterRequest;

import java.util.UUID;

public final class UserCommandFactory {

    public static LoginUserCommand createLoginCommand(LoginRequest loginRequest) {
        return ImmutableLoginUserCommand.builder()
                .email(loginRequest.email())
                .password(loginRequest.password())
                .build();
    }

    public static DeleteUserCommand createDeleteCommand(String id) {
        return ImmutableDeleteUserCommand.builder()
                .id(UUID.fromString(id))
                .build();
    }

    public static RegisterUserCommand createRegisterCommand(RegisterRequest registerRequest) {
        return ImmutableRegisterUserCommand.builder()
                .name(registerRequest.name())
                .email(registerRequest.email())
                .password(registerRequest.password())
                .color(registerRequest.color())
                .build();
    }
}
