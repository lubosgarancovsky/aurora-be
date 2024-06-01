package io.github.lubosgarancovsky.aurora.rest_api.error;

import io.github.lubosgarancovsky.domain.error.ErrorCode;
import io.github.lubosgarancovsky.domain.error.ErrorCodeType;

public enum StoryErrorCode implements ErrorCode {

    STORY_NOT_FOUND(ErrorCodeType.NOT_FOUND, "Story with id %s does not exist.");

    final String template;
    final ErrorCodeType type;

    StoryErrorCode(ErrorCodeType type, String template) {
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
