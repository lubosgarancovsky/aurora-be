package io.github.lubosgarancovsky.aurora.rest_api.controller;

import io.github.lubosgarancovsky.aurora.business.project.usecase.CreateProjectUseCase;
import io.github.lubosgarancovsky.aurora.business.project.usecase.DeleteProjectUseCase;
import io.github.lubosgarancovsky.aurora.business.project.usecase.FindProjectByIdUseCase;
import io.github.lubosgarancovsky.aurora.business.project.usecase.ListProjectsUseCase;
import io.github.lubosgarancovsky.aurora.business.user.service.JwtService;
import io.github.lubosgarancovsky.aurora.domain.listing.ProjectsListingAttribute;
import io.github.lubosgarancovsky.aurora.domain.project.command.ProjectCommandFactory;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ListOfProjects;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectCreatedEntity;
import io.github.lubosgarancovsky.aurora.domain.project.query.FindProjectByIdQuery;
import io.github.lubosgarancovsky.aurora.domain.project.query.ImmutableFindProjectByIdQuery;
import io.github.lubosgarancovsky.aurora.domain.project.query.ImmutableProjectListingQuery;
import io.github.lubosgarancovsky.aurora.domain.project.query.ProjectListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.ImmutableEntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ProjectListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ProjectRequest;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.project.ProjectResponse;
import io.github.lubosgarancovsky.aurora.rest_api.mapper.ProjectMapper;
import io.github.lubosgarancovsky.domain.listing.PageSize;
import io.github.lubosgarancovsky.restapi.listing.ListFilteringParser;
import io.github.lubosgarancovsky.restapi.listing.ListOrderingParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProjectController extends BaseController {

    private final CreateProjectUseCase createProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUsecase;
    private final ListProjectsUseCase listProjectsUseCase;
    private final FindProjectByIdUseCase findProjectByIdUseCase;
    private final JwtService jwtService;
    private final ListFilteringParser listFilteringParser;
    private final ListOrderingParser listOrderingParser;

    public ProjectController(
            CreateProjectUseCase createProjectUseCase,
            DeleteProjectUseCase deleteProjectUsecase,
            ListProjectsUseCase listProjectsUseCase,
            FindProjectByIdUseCase findProjectByIdUseCase,
            JwtService jwtService

    ) {
        this.createProjectUseCase = createProjectUseCase;
        this.deleteProjectUsecase = deleteProjectUsecase;
        this.listProjectsUseCase = listProjectsUseCase;
        this.findProjectByIdUseCase = findProjectByIdUseCase;
        this.jwtService = jwtService;
        this.listFilteringParser = new ListFilteringParser();
        this.listOrderingParser = new ListOrderingParser();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = BASE_PROJECTS_V1
    )
    public EntityCreatedResponse create(
            HttpServletRequest httpRequest,
            @RequestBody ProjectRequest request
    ) {
        String userId = this.jwtService.extractSubject(httpRequest);
        ProjectCreatedEntity entity = this.createProjectUseCase.execute(ProjectCommandFactory.createCreateCommand(request, UUID.fromString(userId)));

        return ImmutableEntityCreatedResponse.builder()
                .id(entity.id().toString())
                .build();
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = PROJECT_DETAIL_URI
    )
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable String id) {
        String userId = this.jwtService.extractSubject(request);
        return ResponseEntity.ok(this.deleteProjectUsecase.execute(ProjectCommandFactory.createDeleteCommand(UUID.fromString(id), UUID.fromString(userId))));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = BASE_PROJECTS_V1
    )
    public ProjectListResponse list(
            HttpServletRequest request,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "page-size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        String userId = this.jwtService.extractSubject(request);

        ProjectListingQuery query = ImmutableProjectListingQuery.builder()
                .userId(UUID.fromString(userId))
                .page(page)
                .pageSize(PageSize.of(50, pageSize))
                .rsqlQuery(listFilteringParser.parse(filter, List.of(ProjectsListingAttribute.values())).asOption())
                .addAllOrderings(listOrderingParser.parse(sort, List.of(ProjectsListingAttribute.values())).asList())
                .build();

        return ProjectMapper.map(this.listProjectsUseCase.execute(query));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = PROJECT_DETAIL_URI
    )
    public ProjectResponse detail(HttpServletRequest httpRequest, @PathVariable String id) {
        String userId = this.jwtService.extractSubject(httpRequest);
        FindProjectByIdQuery query = ImmutableFindProjectByIdQuery.builder()
                        .projectId(UUID.fromString(id))
                        .userId(UUID.fromString(userId))
                        .build();

        return ProjectMapper.map(this.findProjectByIdUseCase.execute(query));
    }
}
