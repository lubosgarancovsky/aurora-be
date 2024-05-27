package io.github.lubosgarancovsky.aurora.rest_api.controller;

import io.github.lubosgarancovsky.aurora.business.user.usecase.LoginUserUseCase;
import io.github.lubosgarancovsky.aurora.business.user.usecase.RegisterUserUseCase;
import io.github.lubosgarancovsky.aurora.domain.user.command.UserCommandFactory;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.LoginRequest;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.RegisterRequest;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.TokensResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController extends BaseController {

    private final LoginUserUseCase loginUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public SecurityController(LoginUserUseCase loginUserUseCase, RegisterUserUseCase registerUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }
    @RequestMapping(
            method = RequestMethod.POST,
            value = LOGIN_URI
    )
    public TokensResponse login(@RequestBody LoginRequest loginRequest) {
        return loginUserUseCase.execute(UserCommandFactory.createLoginCommand(loginRequest));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = REGISTER_URI
    )
    public TokensResponse register(@RequestBody RegisterRequest registerRequest) {
        return registerUserUseCase.execute(UserCommandFactory.createRegisterCommand(registerRequest));
    }

}
