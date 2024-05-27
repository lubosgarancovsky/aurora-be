package io.github.lubosgarancovsky.aurora.business.user.service;

import io.github.lubosgarancovsky.aurora.business.user.repository.JooqUserRepository;
import io.github.lubosgarancovsky.aurora.business.user.usecase.RegisterUserUseCase;
import io.github.lubosgarancovsky.aurora.domain.user.command.RegisterUserCommand;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutableTokensResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.TokensResponse;
import org.springframework.stereotype.Service;

import static io.github.lubosgarancovsky.aurora.rest_api.error.UserErrorCode.USER_ALREADY_EXISTS;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final JooqUserRepository jooqUserRepository;
    private final JwtService jwtService;

    public RegisterUserService(JooqUserRepository jooqUserRepository, JwtService jwtService) {
        this.jooqUserRepository = jooqUserRepository;
        this.jwtService = jwtService;
    }

    @Override
    public TokensResponse execute(RegisterUserCommand registerUserCommand) {
        Boolean exists = jooqUserRepository.existsByEmail(registerUserCommand.email());

        if(exists) {
            throw USER_ALREADY_EXISTS.createError(registerUserCommand.email()).convertToException();
        }

        UserEntity user = jooqUserRepository.insert(registerUserCommand);

        return ImmutableTokensResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(jwtService.generateToken(user))
                .build();
    }
}
