package io.github.lubosgarancovsky.aurora.business.user.service;

import io.github.lubosgarancovsky.aurora.business.user.repository.JooqUserRepository;
import io.github.lubosgarancovsky.aurora.business.user.usecase.LoginUserUseCase;
import io.github.lubosgarancovsky.aurora.domain.user.command.LoginUserCommand;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutableTokensResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static io.github.lubosgarancovsky.aurora.rest_api.error.UserErrorCode.USER_NOT_FOUND;



@Service
public class LoginUserService implements LoginUserUseCase {

    private final JooqUserRepository jooqUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUserService(
        JooqUserRepository jooqUserRepository,
        BCryptPasswordEncoder passwordEncoder,
        JwtService jwtService
        
        ) {
        this.jooqUserRepository = jooqUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public ImmutableTokensResponse execute(LoginUserCommand loginUserCommand) {
        final UserEntity userEntity = jooqUserRepository.findOne(loginUserCommand.email());


        if(!this.passwordEncoder.matches(loginUserCommand.password(), userEntity.password())) {
            throw USER_NOT_FOUND.createError(loginUserCommand.email()).convertToException();
        }

        String accessToken = jwtService.generateToken(userEntity);

        return ImmutableTokensResponse.builder()
                .accessToken(accessToken)
                .refreshToken(accessToken)
                .build();
    }
}
