package io.github.lubosgarancovsky.aurora.rest_api.api_dto.story;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import java.util.Optional;

@JsonSerialize(as = ImmutableStoryRequest.class)
@JsonDeserialize(as = ImmutableStoryRequest.class)
@Value.Immutable
public interface StoryRequest {
    String name();
    String description();
    Boolean inBoard();
    String typeId();
    @Nullable
    String assigneeId();
}
