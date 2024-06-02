package io.github.lubosgarancovsky.aurora.rest_api.api_dto.story;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableSubstoryRequest.class)
@JsonDeserialize(as = ImmutableSubstoryRequest.class)
@Value.Immutable
public interface SubstoryRequest {
    String name();
    String description();
    String stateId();
    String typeId();
    @Nullable
    String assigneeId();
}
