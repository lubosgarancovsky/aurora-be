package io.github.lubosgarancovsky.aurora.rest_api.api_dto.story;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@JsonSerialize(as = ImmutableStoryTypeListResponse.class)
@JsonDeserialize(as = ImmutableStoryTypeListResponse.class)
@Value.Immutable
public interface StoryTypeListResponse {
    List<StoryTypeResponse> items();
}
