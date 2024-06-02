package io.github.lubosgarancovsky.aurora.rest_api.error;

import io.github.lubosgarancovsky.domain.error.ErrorCode;
import io.github.lubosgarancovsky.domain.error.ErrorCodeType;

public enum ProjectErrorCode implements ErrorCode {

    PROJECT_NOT_FOUND(ErrorCodeType.NOT_FOUND, "Project with id %s does not exist."),
    PROJECT_BY_STORY_ID_NOT_FOUND(ErrorCodeType.NOT_FOUND, "Story with id %s does not have any project associated with it.");

    final String template;
    final ErrorCodeType type;

    ProjectErrorCode(ErrorCodeType type, String template) {
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
