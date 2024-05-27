package io.github.lubosgarancovsky.aurora.rest_api.mapper;

import io.github.lubosgarancovsky.aurora.domain.user.entity.ListOfUsers;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.PartnersRecord;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutablePublicUserListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutablePublicUserResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;

public class UserMapper {
    
    public static PublicUserResponse map(UserEntity user) {
        return ImmutablePublicUserResponse.builder()
                .id(user.id().toString())
                .name(user.name())
                .email(user.email())
                .picture(user.picture())
                .color(user.color())
                .build();
    }

    public static PublicUserListResponse map(ListOfUsers list) {
        return ImmutablePublicUserListResponse.builder()
            .totalCount(list.totalCount())
            .pageSize(list.pageSize())
            .page(list.page())
            .addAllItems(list.items().stream().map(UserMapper::map).toList())
            .build();
    }

    public static PublicUserResponse map(PartnersRecord record) {
        return ImmutablePublicUserResponse.builder()
                .id(record.getId().toString())
                .name(record.getName())
                .email(record.getEmail())
                .picture(record.getPicture())
                .color(record.getColor())
                .build();
    }
}
