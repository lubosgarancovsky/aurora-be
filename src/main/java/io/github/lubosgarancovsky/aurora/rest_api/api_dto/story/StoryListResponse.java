package io.github.lubosgarancovsky.aurora.rest_api.api_dto.story;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.restapi.dto.listing.ListingResponse;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableStoryListResponse.class)
@JsonDeserialize(as = ImmutableStoryListResponse.class)
@Value.Immutable
public interface StoryListResponse extends ListingResponse<StoryResponse> {
}
