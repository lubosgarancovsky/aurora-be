package io.github.lubosgarancovsky.aurora.rest_api.api_dto.project;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.lubosgarancovsky.restapi.dto.listing.ListingResponse;
import org.immutables.value.Value;

@JsonSerialize(as = ImmutableProjectListResponse.class)
@JsonDeserialize(as = ImmutableProjectListResponse.class)
@Value.Immutable
public interface ProjectListResponse extends ListingResponse<ProjectResponse> {

}
