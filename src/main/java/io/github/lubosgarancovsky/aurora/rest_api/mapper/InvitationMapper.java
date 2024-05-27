package io.github.lubosgarancovsky.aurora.rest_api.mapper;

import io.github.lubosgarancovsky.aurora.domain.invitation.entity.ListOfInvitations;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.invitation.ImmutableInvitationListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.invitation.InvitationListResponse;

public class InvitationMapper {

    public static InvitationListResponse map(ListOfInvitations list) {
        return ImmutableInvitationListResponse.builder()
            .totalCount(list.totalCount())
            .pageSize(list.pageSize())
            .page(list.page())
            .addAllItems(list.items().stream().toList())
            .build();
    }
}
