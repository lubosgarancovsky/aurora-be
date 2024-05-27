package io.github.lubosgarancovsky.aurora.business.user.service;

import org.springframework.stereotype.Service;

import io.github.lubosgarancovsky.aurora.business.user.repository.JooqUserRepository;
import io.github.lubosgarancovsky.aurora.business.user.usecase.ListUsersUseCase;
import io.github.lubosgarancovsky.aurora.domain.user.entity.ListOfUsers;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.UserListingQuery;

@Service
public class ListUserService implements ListUsersUseCase {

    private final JooqUserRepository jooqUserRepository;

    public ListUserService(JooqUserRepository jooqUserRepository) {
        this.jooqUserRepository = jooqUserRepository;
    }

    @Override
    public ListOfUsers execute(UserListingQuery query) {
        return this.jooqUserRepository.list(query);
    }

}
