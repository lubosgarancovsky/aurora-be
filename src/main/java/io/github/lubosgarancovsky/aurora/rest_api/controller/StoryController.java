package io.github.lubosgarancovsky.aurora.rest_api.controller;

import io.github.lubosgarancovsky.aurora.business.project.usecase.FindProjectByIdUseCase;
import io.github.lubosgarancovsky.aurora.business.story.usecase.*;
import io.github.lubosgarancovsky.aurora.business.user.service.JwtService;
import io.github.lubosgarancovsky.aurora.domain.listing.StoryListingAttribute;
import io.github.lubosgarancovsky.aurora.domain.project.entity.ProjectEntity;
import io.github.lubosgarancovsky.aurora.domain.project.query.ImmutableFindProjectByIdQuery;
import io.github.lubosgarancovsky.aurora.domain.story.command.StoryCommandFactory;
import io.github.lubosgarancovsky.aurora.domain.story.query.ImmutableStoryDetailQuery;
import io.github.lubosgarancovsky.aurora.domain.story.query.ImmutableStoryListingQuery;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryDetailQuery;
import io.github.lubosgarancovsky.aurora.domain.story.query.StoryListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.StoryListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.StoryRequest;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.StoryResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.story.StoryTypeListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.mapper.StoryMapper;
import io.github.lubosgarancovsky.domain.listing.PageSize;
import io.github.lubosgarancovsky.restapi.listing.ListFilteringParser;
import io.github.lubosgarancovsky.restapi.listing.ListOrderingParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StoryController extends BaseController {

    private final FindProjectByIdUseCase findProjectByIdUseCase;
    private final CreateStoryUseCase createStoryUseCase;
    private final ListStoriesUseCase listStoriesUseCase;
    private final ListStoryTypesUseCase listStoryTypesUseCase;
    private final UpdateStoryUseCase updateStoryUseCase;
    private final DetailStoryUseCase detailStoryUseCase;
    private final JwtService jwtService;
    private final ListFilteringParser listFilteringParser;
    private final ListOrderingParser listOrderingParser;

    public StoryController(FindProjectByIdUseCase findProjectByIdUseCase,
                           CreateStoryUseCase createStoryUseCase,
                           ListStoriesUseCase listStoriesUseCase,
                           ListStoryTypesUseCase listStoryTypesUseCase,
                           UpdateStoryUseCase updateStoryUseCase,
                           DetailStoryUseCase detailStoryUseCase,
                           JwtService jwtService) {
        this.findProjectByIdUseCase = findProjectByIdUseCase;
        this.createStoryUseCase = createStoryUseCase;
        this.listStoriesUseCase = listStoriesUseCase;
        this.listStoryTypesUseCase = listStoryTypesUseCase;
        this.updateStoryUseCase = updateStoryUseCase;
        this.detailStoryUseCase = detailStoryUseCase;
        this.jwtService = jwtService;
        this.listFilteringParser = new ListFilteringParser();
        this.listOrderingParser = new ListOrderingParser();
    }

    @RequestMapping(
            path = STORIES_BY_PROJECT_URI,
            method = RequestMethod.POST
    )
    public EntityCreatedResponse create(
            HttpServletRequest httpRequest,
            @PathVariable String id,
            @RequestBody StoryRequest request
            ) {
        String userId = jwtService.extractSubject(httpRequest);
        ProjectEntity project = this.findProjectByIdUseCase.execute(
                ImmutableFindProjectByIdQuery.builder()
                        .projectId(UUID.fromString(id))
                        .userId(UUID.fromString(userId)).build());

        return this.createStoryUseCase.execute(StoryCommandFactory.createStoryCommand(request, project, userId));
    }

    @RequestMapping(
            path = BASE_STORY_V1,
            method = RequestMethod.GET
    )
    public StoryListResponse list(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "page-size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        StoryListingQuery query = ImmutableStoryListingQuery.builder()
                .page(page)
                .pageSize(PageSize.of(50, pageSize))
                .rsqlQuery(listFilteringParser.parse(filter, List.of(StoryListingAttribute.values())).asOption())
                .addAllOrderings(listOrderingParser.parse(sort, List.of(StoryListingAttribute.values())).asList())
                .build();

        return StoryMapper.map(this.listStoriesUseCase.execute(query));
    }

    @RequestMapping(
            path = STORY_TYPE_URI,
            method = RequestMethod.GET
    )
    public StoryTypeListResponse listStoryTypes() {
        return StoryMapper.map(this.listStoryTypesUseCase.execute());
    }

    @RequestMapping(
            path = STORY_DETAIL_URI,
            method = RequestMethod.PUT
    )
    public StoryResponse update(@PathVariable String id, @RequestBody StoryRequest request) {
        this.updateStoryUseCase.execute(StoryCommandFactory.updateStoryCommand(id, request));

        StoryDetailQuery query = ImmutableStoryDetailQuery.builder()
                .storyId(UUID.fromString(id))
                .build();

        return StoryMapper.map(this.detailStoryUseCase.execute(query));
    }

    @RequestMapping(
            path = STORY_DETAIL_URI,
            method = RequestMethod.GET
    )
    public StoryResponse detail(@PathVariable String id) {
        StoryDetailQuery query = ImmutableStoryDetailQuery.builder()
                .storyId(UUID.fromString(id))
                .build();

        return StoryMapper.map(this.detailStoryUseCase.execute(query));
    }
}
