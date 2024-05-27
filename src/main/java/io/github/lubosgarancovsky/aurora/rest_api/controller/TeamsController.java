package io.github.lubosgarancovsky.aurora.rest_api.controller;

import io.github.lubosgarancovsky.aurora.business.team.usecase.CreateTeamUseCase;
import io.github.lubosgarancovsky.aurora.business.team.usecase.FindTeamByProjectIdUseCase;
import io.github.lubosgarancovsky.aurora.business.user.service.JwtService;
import io.github.lubosgarancovsky.aurora.domain.team.command.TeamCommandFactory;
import io.github.lubosgarancovsky.aurora.domain.team.entity.TeamEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TeamsController extends  BaseController {

    private final JwtService jwtService;
    private final CreateTeamUseCase createTeamUseCase;
    private final FindTeamByProjectIdUseCase findTeamByProjectIdUseCase;

    public TeamsController(JwtService jwtService, CreateTeamUseCase createTeamUseCase, FindTeamByProjectIdUseCase findTeamByProjectIdUseCase) {
        this.jwtService = jwtService;
        this.createTeamUseCase = createTeamUseCase;
        this.findTeamByProjectIdUseCase = findTeamByProjectIdUseCase;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = PROJECT_TEAM_URI
    )
    public ResponseEntity<?> create(HttpServletRequest httpRequest, @PathVariable String id) {
        UUID userId = UUID.fromString(this.jwtService.extractSubject(httpRequest));
        return ResponseEntity.ok(this.createTeamUseCase.execute(TeamCommandFactory.createTeamCommand(id, userId)));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = PROJECT_TEAM_URI
    )
    public TeamEntity findByProjectId(@PathVariable String id) {
        return this.findTeamByProjectIdUseCase.execute(TeamCommandFactory.findTeamByProjectIdCommand(id));
    }

//    public ResponseEntity<?> delete(HttpServletRequest httpRequest, @PathVariable String id) {
//        UUID userId = UUID.fromString(this.jwtService.extractSubject(httpRequest));
//        return ResponseEntity.ok(this.deleteTeamUseCase.execute(TeamCommandFactory.deleteTeamCommand(id, userId)));
//
//    }
}
