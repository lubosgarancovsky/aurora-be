package io.github.lubosgarancovsky.aurora.rest_api.controller;

import io.github.lubosgarancovsky.aurora.business.invitation.usecase.AcceptInvitationUseCase;
import io.github.lubosgarancovsky.aurora.business.invitation.usecase.CreateInvitationUseCase;
import io.github.lubosgarancovsky.aurora.business.invitation.usecase.DeleteInvitationUseCase;
import io.github.lubosgarancovsky.aurora.business.invitation.usecase.ListInvitationUseCase;
import io.github.lubosgarancovsky.aurora.business.user.service.JwtService;
import io.github.lubosgarancovsky.aurora.domain.invitation.command.InvitationCommandFactory;
import io.github.lubosgarancovsky.aurora.domain.invitation.query.ImmutableInvitationListingQuery;
import io.github.lubosgarancovsky.aurora.domain.invitation.query.InvitationListingQuery;
import io.github.lubosgarancovsky.aurora.domain.listing.InvitationsListingAttribute;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.EntityCreatedResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.invitation.InvitationListResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.invitation.InvitationRequest;
import io.github.lubosgarancovsky.aurora.rest_api.mapper.InvitationMapper;
import io.github.lubosgarancovsky.domain.listing.PageSize;
import io.github.lubosgarancovsky.restapi.listing.ListFilteringParser;
import io.github.lubosgarancovsky.restapi.listing.ListOrderingParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class InvitationController extends BaseController {

    private final JwtService jwtService;
    private final CreateInvitationUseCase createInvitationUseCase;
    private final DeleteInvitationUseCase deleteInvitationUseCase;
    private final AcceptInvitationUseCase acceptInvitationUseCase;
    private final ListInvitationUseCase listInvitationUseCase;
    private final ListFilteringParser listFilteringParser;
    private final ListOrderingParser listOrderingParser;

    public InvitationController(
            JwtService jwtService,
            CreateInvitationUseCase createInvitationUseCase,
            DeleteInvitationUseCase deleteInvitationUseCase,
            ListInvitationUseCase listInvitationUseCase,
            AcceptInvitationUseCase acceptInvitationUseCase
    ) {
        this.jwtService = jwtService;
        this.createInvitationUseCase = createInvitationUseCase;
        this.deleteInvitationUseCase = deleteInvitationUseCase;
        this.listInvitationUseCase = listInvitationUseCase;
        this.acceptInvitationUseCase = acceptInvitationUseCase;
        this.listFilteringParser = new ListFilteringParser();
        this.listOrderingParser = new ListOrderingParser();
    }

    @RequestMapping(
            path = BASE_INVITATION_V1,
            method = RequestMethod.POST
    )
    public EntityCreatedResponse create(HttpServletRequest httpRequest, @RequestBody InvitationRequest request) {
        String userId = this.jwtService.extractSubject(httpRequest);
        return this.createInvitationUseCase.execute(InvitationCommandFactory.createInvitationCommand(userId, request));
    }

    @RequestMapping(
            path = INVITATION_DETAIL_URI,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<?> delete(HttpServletRequest httpRequest, @PathVariable String id) {
        String userId = this.jwtService.extractSubject(httpRequest);
        return ResponseEntity.ok(this.deleteInvitationUseCase.execute(InvitationCommandFactory.createDeleteInvitationCommand(userId, id)));
    }

    @RequestMapping(
            path = INVITATION_DETAIL_URI,
            method = RequestMethod.POST
    )
    public ResponseEntity<?> accept(HttpServletRequest httpRequest, @PathVariable String id) {
        String userId = this.jwtService.extractSubject(httpRequest);
        return ResponseEntity.ok(this.acceptInvitationUseCase.execute(InvitationCommandFactory.createAcceptInvitationCommand(userId, id)));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/v1/invitations"
    )
    public InvitationListResponse list(
            HttpServletRequest httpRequest,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "page-size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        String userId = this.jwtService.extractSubject(httpRequest);

        InvitationListingQuery query = ImmutableInvitationListingQuery.builder()
                .userId(UUID.fromString(userId))
                .page(page)
                .pageSize(PageSize.of(50, pageSize))
                .rsqlQuery(listFilteringParser.parse(filter, List.of(InvitationsListingAttribute.values())).asOption())
                .addAllOrderings(listOrderingParser.parse(sort, List.of(InvitationsListingAttribute.values())).asList())
                .build();

        return InvitationMapper.map(this.listInvitationUseCase.execute(query));
    }

}
