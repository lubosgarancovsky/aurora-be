package io.github.lubosgarancovsky.aurora.rest_api.error;

import io.github.lubosgarancovsky.domain.error.ErrorCode;
import io.github.lubosgarancovsky.domain.error.ErrorCodeType;

public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(ErrorCodeType.NOT_FOUND, "User with id %s was not found."),
    USER_ALREADY_EXISTS(ErrorCodeType.BAD_REQUEST, "User with email %s already exists.");

    final String template;
    final ErrorCodeType type;

    UserErrorCode(ErrorCodeType type, String template) {
        this.type = type;
        this.template = template;
    }

    @Override
    public String template() {
        return template;
    }

    @Override
    public ErrorCodeType type() {
        return this.type;
    }
}
