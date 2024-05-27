package io.github.lubosgarancovsky.aurora.rest_api.api_dto.board;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;


@JsonSerialize(as = ImmutableBoardEntryResponse.class)
@JsonDeserialize(as = ImmutableBoardEntryResponse.class)
@Value.Immutable
public interface BoardEntryResponse {

    String id();
    String name();
    String code();
}
