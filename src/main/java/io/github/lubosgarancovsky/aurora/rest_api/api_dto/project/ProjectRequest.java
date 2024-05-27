package io.github.lubosgarancovsky.aurora.rest_api.api_dto.project;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@JsonSerialize(as = ImmutableProjectRequest.class)
@JsonDeserialize(as = ImmutableProjectRequest.class)
@Value.Immutable
public interface ProjectRequest {

    String name();

    String description();

    String code();

    default List<ProjectLinkRequest> links() {
        return List.of();
    }

}
