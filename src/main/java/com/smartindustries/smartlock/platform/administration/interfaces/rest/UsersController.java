package com.smartindustries.smartlock.platform.administration.interfaces.rest;

import com.smartindustries.smartlock.platform.administration.application.commandservices.MembershipCommandService;
import com.smartindustries.smartlock.platform.administration.application.queryservices.MembershipQueryService;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.UpdateUserRoleInOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetUsersByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.UserWithRoleResource;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.transform.UserWithRoleResourceFromPersistenceAssembler;
import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User management and query endpoints")
public class UsersController {

    private final MembershipQueryService membershipQueryService;
    private final MembershipCommandService membershipCommandService;

    public UsersController(MembershipQueryService membershipQueryService, MembershipCommandService membershipCommandService) {
        this.membershipQueryService = membershipQueryService;
        this.membershipCommandService = membershipCommandService;
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

    @PutMapping("/{userId}/role")
    @Operation(
        summary = "Update user role in organization",
        description = "Changes the role of a user within a specific organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Role updated successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserWithRoleResource.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input or role does not belong to organization",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "User is not a member of this organization",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<?> updateUserRole(@PathVariable Long userId, @RequestParam Long organizationId, @RequestParam Long roleId) {
        var command = new UpdateUserRoleInOrganizationCommand(userId, organizationId, roleId);
        var result = membershipCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                entity -> {
                    var resource = new UserWithRoleResource(entity.getUserId(), "", "", "", entity.getRoleId(), "");
                    return resource;
                },
                HttpStatus.OK);
    }
}
