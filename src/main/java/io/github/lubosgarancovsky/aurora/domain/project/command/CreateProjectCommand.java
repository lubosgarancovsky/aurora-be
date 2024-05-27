package io.github.lubosgarancovsky.aurora.domain.project.command;

import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ProjectLinkRequest;
import io.github.lubosgarancovsky.domain.marker.Command;
import org.immutables.value.Value;

import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface CreateProjectCommand extends Command {

    String name();

    String description();

    String code();

    UUID createdBy();

    List<ProjectLinkRequest> links();

}
