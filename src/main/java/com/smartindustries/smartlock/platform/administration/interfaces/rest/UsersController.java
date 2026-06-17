package com.smartindustries.smartlock.platform.administration.interfaces.rest;

import com.smartindustries.smartlock.platform.administration.application.queryservices.MembershipQueryService;
import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetUsersByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.UserWithRoleResource;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.transform.UserWithRoleResourceFromPersistenceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User management and query endpoints")
public class UsersController {

    private final MembershipQueryService membershipQueryService;

    public UsersController(MembershipQueryService membershipQueryService) {
        this.membershipQueryService = membershipQueryService;
    }

    @GetMapping
    @Operation(
        summary = "Get users by organization",
        description = "Returns all users belonging to a specific organization, including their role."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Users retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserWithRoleResource.class)
            )
        )
    })
    public ResponseEntity<List<UserWithRoleResource>> getUsersByOrganization(@RequestParam Long organizationId) {
        var entities = membershipQueryService.handle(new GetUsersByOrganizationIdQuery(organizationId));
        var resources = entities.stream()
                .map(UserWithRoleResourceFromPersistenceAssembler::toResourceFromPersistence)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
