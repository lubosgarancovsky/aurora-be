package io.github.lubosgarancovsky.aurora.rest_api.controller;

import io.github.lubosgarancovsky.aurora.business.board.usecase.ListBoardEntitiesUseCase;
import io.github.lubosgarancovsky.aurora.business.board.usecase.UpdateBoardUseCase;
import io.github.lubosgarancovsky.aurora.business.user.service.JwtService;
import io.github.lubosgarancovsky.aurora.domain.board.command.BoardCommandFactory;
import io.github.lubosgarancovsky.aurora.domain.board.query.BoardListingQuery;
import io.github.lubosgarancovsky.aurora.domain.board.query.ImmutableBoardListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.BoardRequest;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.BoardResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BoardController extends BaseController {
    private final JwtService jwtService;

    private final UpdateBoardUseCase updateBoardUseCase;

    private final ListBoardEntitiesUseCase listBoardEntitiesUseCase;

    public BoardController(
            ListBoardEntitiesUseCase listBoardEntitiesUseCase,
            JwtService jwtService,
            UpdateBoardUseCase updateBoardUseCase

    ) {
        this.listBoardEntitiesUseCase = listBoardEntitiesUseCase;
        this.jwtService = jwtService;
        this.updateBoardUseCase = updateBoardUseCase;
    }


    @RequestMapping(
            path = PROJECT_BOARD_URI,
            method = RequestMethod.GET
    )
    public BoardResponse list(@PathVariable String id) {
        BoardListingQuery query = ImmutableBoardListingQuery.builder()
                .projectId(UUID.fromString(id))
                .build();

        return this.listBoardEntitiesUseCase.execute(query);
    }

    @RequestMapping(
            path = PROJECT_BOARD_URI,
            method = RequestMethod.PUT
    )
    public BoardResponse update(
            HttpServletRequest httpServletRequest,
            @RequestBody BoardRequest request,
            @PathVariable String id) {

        String userId = this.jwtService.extractSubject(httpServletRequest);
        this.updateBoardUseCase.execute(BoardCommandFactory.createUpdateCommand(request, id, userId));

        BoardListingQuery query = ImmutableBoardListingQuery.builder()
                .projectId(UUID.fromString(id))
                .build();

        return this.listBoardEntitiesUseCase.execute(query);
    }
}
