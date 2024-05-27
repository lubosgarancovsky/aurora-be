package io.github.lubosgarancovsky.aurora.rest_api.error;

import io.github.lubosgarancovsky.domain.error.ErrorCode;
import io.github.lubosgarancovsky.domain.error.ErrorCodeType;


public enum InvitationErrorCode implements ErrorCode {

    FORBIDDEN_FOR_USER(ErrorCodeType.FORBIDDEN, "You don't have a permission to interact with this invitation."),
    NOT_FOUND(ErrorCodeType.NOT_FOUND, "Invitation with id %s does not exist."),
    CANNOT_ACCEPT(ErrorCodeType.FORBIDDEN, "You have no permission to accept the invitation with id %s.");

    final String template;
    final ErrorCodeType type;

    InvitationErrorCode(ErrorCodeType type, String template) {
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
