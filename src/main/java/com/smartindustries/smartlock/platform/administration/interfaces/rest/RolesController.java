package com.smartindustries.smartlock.platform.administration.interfaces.rest;

import com.smartindustries.smartlock.platform.administration.application.commandservices.RoleCommandService;
import com.smartindustries.smartlock.platform.administration.application.queryservices.RoleQueryService;
import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetRolesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.AddRoleToOrganizationResource;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.RoleResource;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.transform.AddRoleToOrganizationCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.transform.RoleResourceFromPersistenceAssembler;
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
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Role management endpoints")
public class RolesController {

    private final RoleQueryService roleQueryService;
    private final RoleCommandService roleCommandService;

    public RolesController(RoleQueryService roleQueryService, RoleCommandService roleCommandService) {
        this.roleQueryService = roleQueryService;
        this.roleCommandService = roleCommandService;
    }

    @GetMapping
    @Operation(
        summary = "Get roles by organization",
        description = "Returns all roles belonging to a specific organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Roles retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RoleResource.class)
            )
        )
    })
    public ResponseEntity<List<RoleResource>> getRolesByOrganization(@RequestParam Long organizationId) {
        var entities = roleQueryService.handle(new GetRolesByOrganizationIdQuery(organizationId));
        var resources = entities.stream()
                .map(RoleResourceFromPersistenceAssembler::toResourceFromPersistence)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    @Operation(
        summary = "Add role to organization",
        description = "Creates a new role within an existing organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Role created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RoleResource.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Conflict - role name already exists in this organization",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<?> addRole(@RequestBody AddRoleToOrganizationResource resource) {
        var command = AddRoleToOrganizationCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = roleCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                RoleResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }
}
