package io.github.lubosgarancovsky.aurora.rest_api.controller;

import java.util.List;
import java.util.UUID;

import io.github.lubosgarancovsky.aurora.business.user.usecase.DeleteUserUseCase;
import io.github.lubosgarancovsky.aurora.domain.user.command.DeleteUserCommand;
import io.github.lubosgarancovsky.aurora.domain.user.command.ImmutableDeleteUserCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.lubosgarancovsky.aurora.business.user.usecase.FindUserByIdUseCase;
import io.github.lubosgarancovsky.aurora.business.user.usecase.ListUsersUseCase;
import io.github.lubosgarancovsky.aurora.domain.listing.UsersListingAttribute;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.FindUserByIdQuery;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.ImmutableUserListingQuery;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.UserListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import io.github.lubosgarancovsky.aurora.rest_api.mapper.UserMapper;
import io.github.lubosgarancovsky.domain.listing.PageSize;
import io.github.lubosgarancovsky.restapi.listing.ListFilteringParser;
import io.github.lubosgarancovsky.restapi.listing.ListOrderingParser;

@RestController
public class UserController extends BaseController {

    private final FindUserByIdUseCase findUserByIdUseCase;
    private final ListUsersUseCase listUsersUsecase;
    private final ListFilteringParser listFilteringParser;
    private final ListOrderingParser listOrderingParser;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(
        FindUserByIdUseCase findUserByIdUseCase,
        ListUsersUseCase listUsersUsecase,
        DeleteUserUseCase deleteUserUseCase
        ) {
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.listUsersUsecase = listUsersUsecase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.listFilteringParser = new ListFilteringParser();
        this.listOrderingParser = new ListOrderingParser();
    }
    
    @RequestMapping(
        method = RequestMethod.GET,
        value = USER_DETAIL_URI
    )
    public PublicUserResponse detail(@PathVariable String id) {
        return UserMapper.map(this.findUserByIdUseCase.execute(FindUserByIdQuery.of(UUID.fromString(id))));
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = BASE_USERS_V1
    )
    public PublicUserListResponse list(
        @RequestParam(name = "page", required = false, defaultValue = "1") int page,
        @RequestParam(name = "page-size", required = false, defaultValue = "10") int pageSize,
        @RequestParam(name = "filter", required = false) String filter,
        @RequestParam(name = "sort", required = false) String sort
    ) {


        UserListingQuery query = ImmutableUserListingQuery.builder()
                                    .page(page)
                                    .pageSize(PageSize.of(50, pageSize))
                                    .rsqlQuery(listFilteringParser.parse(filter, List.of(UsersListingAttribute.values())).asOption())
                                    .addAllOrderings(listOrderingParser.parse(sort, List.of(UsersListingAttribute.values())).asList())
                                    .build();


        return UserMapper.map(this.listUsersUsecase.execute(query));
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = USER_DETAIL_URI
    )
    public String delete(@PathVariable String id) {
        DeleteUserCommand command = ImmutableDeleteUserCommand.builder()
                .id(UUID.fromString(id))
                .build();

        return this.deleteUserUseCase.execute(command);
    }
}
