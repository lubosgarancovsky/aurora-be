package io.github.lubosgarancovsky.aurora.rest_api.api_dto.board;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableStoryStateResponse.class)
@JsonDeserialize(as = ImmutableStoryStateResponse.class)
@Value.Immutable
public interface StoryStateResponse {

    String id();

    String name();

    String code();
}
