package io.github.lubosgarancovsky.aurora.rest_api.controller;


public abstract class BaseController {

    public static final String BASE_AUTH_V1 = "/v1/auth";

    public static final String LOGIN_URI = BASE_AUTH_V1 + "/login";

    public static final String BASE_USERS_V1 = "/v1/users";

    public static final String ID_PARAM = "/{id}";

    public static final String USER_DETAIL_URI = BASE_USERS_V1 + ID_PARAM;

    public static final String REGISTER_URI = BASE_AUTH_V1 + "/register";

    public static final String BASE_PROJECTS_V1 = "/v1/projects";

    public static final String PROJECT_DETAIL_URI = BASE_PROJECTS_V1 + ID_PARAM;

    public static final String PROJECT_TEAM_URI = BASE_PROJECTS_V1 + ID_PARAM + "/teams";

    public static final String BASE_INVITATION_V1 = "/v1/invitations";

    public static final String INVITATION_DETAIL_URI = BASE_INVITATION_V1 + "/{id}";

    public static final String PROJECT_BOARD_URI = BASE_PROJECTS_V1 + ID_PARAM + "/board";

    public static final String BASE_STATES_V1 = "/v1/states";
}
