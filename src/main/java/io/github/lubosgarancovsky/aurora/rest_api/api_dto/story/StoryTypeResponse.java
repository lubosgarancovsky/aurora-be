package io.github.lubosgarancovsky.aurora.rest_api.api_dto.story;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableStoryTypeResponse.class)
@JsonDeserialize(as = ImmutableStoryTypeResponse.class)
@Value.Immutable
public interface StoryTypeResponse {
    String id();
    String name();
    String code();
}
