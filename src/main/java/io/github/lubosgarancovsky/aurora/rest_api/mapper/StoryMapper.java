package io.github.lubosgarancovsky.aurora.rest_api.mapper;

import io.github.lubosgarancovsky.aurora.domain.board.entity.StoryStateEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.StoryTypeEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.SubstoryEntity;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.ImmutableStoryStateResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.StoryStateResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.*;

import java.util.List;

public class StoryMapper {

    public static StoryStateResponse map(StoryStateEntity state) {
        return ImmutableStoryStateResponse.builder()
                .id(state.id().toString())
                .name(state.name())
                .code(state.code())
                .build();
    }

    public static StoryTypeResponse map(StoryTypeEntity type) {
        return ImmutableStoryTypeResponse.builder()
                .id(type.id().toString())
                .name(type.name())
                .code(type.code())
                .build();
    }

    public static SubstoryResponse map(SubstoryEntity substory) {
        return ImmutableSubstoryResponse.builder()
                .id(substory.id().toString())
                .name(substory.name())
                .description(substory.description())
                .code(substory.code())
                .createdAt(substory.createdAt())
                .createdBy(substory.createdBy())
                .assignedTo(substory.assignedTo())
                .state(StoryMapper.map(substory.state()))
                .type(StoryMapper.map(substory.type()))
                .build();
    }

    public static StoryResponse map(StoryEntity story) {
        return ImmutableStoryResponse.builder()
                .id(story.id().toString())
                .name(story.name())
                .description(story.description())
                .code(story.code())
                .createdAt(story.createdAt())
                .createdBy(story.createdBy())
                .assignedTo(story.assignedTo())
                .substories(story.substories().stream().map(StoryMapper::map).toList())
                .state(StoryMapper.map(story.state()))
                .type(StoryMapper.map(story.type()))
                .build();
    }

    public static StoryListResponse map(ListOfStories list) {
        return ImmutableStoryListResponse.builder()
                .totalCount(list.totalCount())
                .pageSize(list.pageSize())
                .page(list.page())
                .addAllItems(list.items().stream().map(StoryMapper::map).toList())
                .build();
    }

    public static StoryTypeListResponse map(List<StoryTypeEntity> list) {
        return ImmutableStoryTypeListResponse.builder()
                .items(list.stream().map(StoryMapper::map).toList())
                .build();
    }
}
