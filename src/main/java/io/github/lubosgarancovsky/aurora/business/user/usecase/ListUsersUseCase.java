package io.github.lubosgarancovsky.aurora.business.user.usecase;

import io.github.lubosgarancovsky.aurora.domain.user.entity.ListOfUsers;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.UserListingQuery;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface ListUsersUseCase extends UseCaseQuery<UserListingQuery, ListOfUsers> {
    
}
