package io.github.lubosgarancovsky.aurora.rest_api.api_dto.board;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@JsonSerialize(as = ImmutableBoardResponse.class)
@JsonDeserialize(as = ImmutableBoardResponse.class)
@Value.Immutable
public interface BoardResponse {

    List<BoardEntryResponse> items();
}
