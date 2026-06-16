package com.smartindustries.smartlock.platform.administration.interfaces.rest;

import com.smartindustries.smartlock.platform.administration.application.queryservices.RoleQueryService;
import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetRolesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.RoleResource;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.transform.RoleResourceFromPersistenceAssembler;
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
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Role management endpoints")
public class RolesController {

    private final RoleQueryService roleQueryService;

    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
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
}
