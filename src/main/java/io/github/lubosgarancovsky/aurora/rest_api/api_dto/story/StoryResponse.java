package io.github.lubosgarancovsky.aurora.rest_api.api_dto.story;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.StoryStateResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.user.PublicUserResponse;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;
import java.time.LocalDateTime;
import java.util.List;

@JsonSerialize(as = ImmutableStoryResponse.class)
@JsonDeserialize(as = ImmutableStoryResponse.class)
@Value.Immutable
public interface StoryResponse {

    String id();
    String name();
    String description();
    String code();

    PublicUserResponse createdBy();
    @Nullable
    PublicUserResponse assignedTo();
    LocalDateTime createdAt();

    StoryStateResponse state();
    StoryTypeResponse type();

    Boolean inBoard();

    List<SubstoryResponse> substories();

}
