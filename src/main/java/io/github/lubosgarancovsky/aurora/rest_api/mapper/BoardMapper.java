package io.github.lubosgarancovsky.aurora.rest_api.mapper;

import io.github.lubosgarancovsky.aurora.domain.board.entity.StoryStateEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.ImmutableStoryStateListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.ImmutableStoryStateResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.StoryStateListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.StoryStateResponse;

import java.util.List;

public class BoardMapper {

    public static StoryStateResponse map(StoryStateEntity entity) {
        return ImmutableStoryStateResponse.builder()
                .id(entity.id().toString())
                .name(entity.name())
                .code(entity.code())
                .build();
    }

    public static StoryStateListResponse map(List<StoryStateEntity> entities) {
        return ImmutableStoryStateListResponse.builder()
                .items(entities.stream().map(BoardMapper::map).toList())
                .build();
    }
}
