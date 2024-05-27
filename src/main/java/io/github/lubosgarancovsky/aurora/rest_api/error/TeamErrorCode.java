package io.github.lubosgarancovsky.aurora.rest_api.error;

import io.github.lubosgarancovsky.domain.error.ErrorCode;
import io.github.lubosgarancovsky.domain.error.ErrorCodeType;

public enum TeamErrorCode implements ErrorCode {

    TEAM_ALREADY_EXISTS_ON_PROJECT(ErrorCodeType.BAD_REQUEST, "Project with id %s already has a team.");

    final String template;
    final ErrorCodeType type;

    TeamErrorCode(ErrorCodeType type, String template) {
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
