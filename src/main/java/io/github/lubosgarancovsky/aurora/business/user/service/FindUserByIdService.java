package io.github.lubosgarancovsky.aurora.business.user.service;

import org.springframework.stereotype.Service;

import io.github.lubosgarancovsky.aurora.business.user.repository.JooqUserRepository;
import io.github.lubosgarancovsky.aurora.business.user.usecase.FindUserByIdUseCase;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.FindUserByIdQuery;

@Service
public class FindUserByIdService implements FindUserByIdUseCase {

    private final JooqUserRepository jooqUserRepository;

    public FindUserByIdService(JooqUserRepository jooqUserRepository) {
        this.jooqUserRepository = jooqUserRepository;
    }

    @Override
    public UserEntity execute(FindUserByIdQuery query) {
        return this.jooqUserRepository.findOne(query);
    }
    
}
