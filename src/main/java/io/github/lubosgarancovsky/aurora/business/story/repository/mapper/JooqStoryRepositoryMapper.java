package io.github.lubosgarancovsky.aurora.business.story.repository.mapper;

import io.github.lubosgarancovsky.aurora.domain.board.entity.ImmutableStoryStateEntity;
import io.github.lubosgarancovsky.aurora.domain.story.entity.*;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.ImmutableListOfStories;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.ListOfStories;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.ImmutablePublicUserResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import io.github.lubosgarancovsky.persistance.jooq.handler.mapper.TriFunction;
import org.jooq.Record;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class JooqStoryRepositoryMapper {

    public static List<StoryEntity> map(Stream<Record> records) {
        Map<UUID, StoryEntity> stories = new HashMap<>();
        Map<UUID, List<SubstoryEntity>> substories = new HashMap<>();

        records.forEach(record -> {
            UUID storyId = record.getValue("storyId", UUID.class);
            UUID substoryId = record.getValue("subStoryId", UUID.class);

            if (!stories.containsKey(storyId)) {
                UUID assigneeId = record.getValue("assignedToId", UUID.class);
                PublicUserResponse assignedTo = assigneeId == null ? null : ImmutablePublicUserResponse.builder()
                        .id(assigneeId.toString())
                        .name(record.getValue("assignedToName", String.class))
                        .email(record.getValue("assignedToEmail", String.class))
                        .color(record.getValue("assignedToColor", String.class))
                        .picture(record.getValue("assignedToPicture", String.class))
                        .build();

                stories.put(storyId, ImmutableStoryEntity.builder()
                        .id(storyId)
                        .name(record.getValue("storyName", String.class))
                        .description(record.getValue("storyDescription", String.class))
                        .code(record.getValue("storyCode", String.class))
                        .inBoard(record.getValue("storyInBoard", Boolean.class))
                        .state(ImmutableStoryStateEntity.builder()
                                .id(record.getValue("storyStateId", UUID.class))
                                .name(record.getValue("storyStateName", String.class))
                                .code(record.getValue("storyStateCode", String.class))
                                .build())
                        .type(ImmutableStoryTypeEntity.builder()
                                .id(record.getValue("storyTypeId", UUID.class))
                                .name(record.getValue("storyTypeName", String.class))
                                .code(record.getValue("storyTypeCode", String.class))
                                .build())
                        .createdAt(record.getValue("storyCreatedAt", LocalDateTime.class))
                        .createdBy(ImmutablePublicUserResponse.builder()
                                .id(record.getValue("createdById", UUID.class).toString())
                                .name(record.getValue("createdByName", String.class))
                                .email(record.getValue("createdByEmail", String.class))
                                .color(record.getValue("createdByColor", String.class))
                                .picture(record.getValue("createdByPicture", String.class))
                                .build())
                        .assignedTo(assignedTo)
                        .substories(new ArrayList<>())
                        .build());
            }

            if (substoryId != null) {
                UUID assigneeId = record.getValue("subStoryAssignedToId", UUID.class);
                PublicUserResponse assignedTo = assigneeId == null ? null : ImmutablePublicUserResponse.builder()
                        .id(assigneeId.toString())
                        .name(record.getValue("subStoryAssignedToName", String.class))
                        .email(record.getValue("subStoryAssignedToEmail", String.class))
                        .color(record.getValue("subStoryAssignedToColor", String.class))
                        .picture(record.getValue("subStoryAssignedToPicture", String.class))
                        .build();

                SubstoryEntity substory = ImmutableSubstoryEntity.builder()
                        .id(substoryId)
                        .parentId(record.getValue("subStoryParentId", UUID.class))
                        .name(record.getValue("subStoryName", String.class))
                        .description(record.getValue("subStoryDescription", String.class))
                        .code(record.getValue("subStoryCode", String.class))
                        .state(ImmutableStoryStateEntity.builder()
                                .id(record.getValue("subStoryStateId", UUID.class))
                                .name(record.getValue("subStoryStateName", String.class))
                                .code(record.getValue("subStoryStateCode", String.class))
                                .build())
                        .type(ImmutableStoryTypeEntity.builder()
                                .id(record.getValue("subStoryTypeId", UUID.class))
                                .name(record.getValue("subStoryTypeName", String.class))
                                .code(record.getValue("subStoryTypeCode", String.class))
                                .build())
                        .createdAt(record.getValue("subStoryCreatedAt", LocalDateTime.class))
                        .createdBy(ImmutablePublicUserResponse.builder()
                                .id(record.getValue("subStoryCreatedById", UUID.class).toString())
                                .name(record.getValue("subStoryCreatedByName", String.class))
                                .email(record.getValue("subStoryCreatedByEmail", String.class))
                                .color(record.getValue("subStoryCreatedByColor", String.class))
                                .picture(record.getValue("subStoryCreatedByPicture", String.class))
                                .build())
                        .assignedTo(assignedTo)
                        .build();

                substories.computeIfAbsent(storyId, k -> new ArrayList<>()).add(substory);
            }
        });

        return stories.values().stream().map(story -> (StoryEntity) ImmutableStoryEntity.builder()
                .id(story.id())
                .name(story.name())
                .description(story.description())
                .code(story.code())
                .inBoard(story.inBoard())
                .state(story.state())
                .type(story.type())
                .createdAt(story.createdAt())
                .createdBy(story.createdBy())
                .assignedTo(story.assignedTo())
                .substories(substories.getOrDefault(story.id(), new ArrayList<>()))
                .build()).toList();
    }

    public static final TriFunction<Stream<Record>, Integer, StoryListingQuery, ListOfStories>
            listingMapper =
            (records, totalCount, query) ->
                    ImmutableListOfStories.builder()
                            .page(query.page())
                            .pageSize(query.pageSize().value())
                            .totalCount(totalCount)
                            .addAllItems(map(records))
                            .build();
}
