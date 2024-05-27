package io.github.lubosgarancovsky.aurora.business.user.usecase;


import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.FindUserByIdQuery;
import io.github.lubosgarancovsky.port.UseCaseQuery;

public interface FindUserByIdUseCase extends UseCaseQuery<FindUserByIdQuery, UserEntity> {}
