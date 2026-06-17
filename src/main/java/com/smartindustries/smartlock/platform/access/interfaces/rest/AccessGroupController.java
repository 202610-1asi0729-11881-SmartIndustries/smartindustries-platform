package com.smartindustries.smartlock.platform.access.interfaces.rest;

import com.smartindustries.smartlock.platform.access.application.commandservices.AccessGroupCommandService;
import com.smartindustries.smartlock.platform.access.application.queryservices.AccessGroupQueryService;
import com.smartindustries.smartlock.platform.access.domain.model.queries.GetAccessGroupsByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.access.interfaces.rest.resources.AccessGroupResource;
import com.smartindustries.smartlock.platform.access.interfaces.rest.resources.CreateAccessGroupResource;
import com.smartindustries.smartlock.platform.access.interfaces.rest.transform.AccessGroupResourceFromEntityAssembler;
import com.smartindustries.smartlock.platform.access.interfaces.rest.transform.AccessGroupResourceFromPersistenceAssembler;
import com.smartindustries.smartlock.platform.access.interfaces.rest.transform.CreateAccessGroupCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/access-groups", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Access Groups", description = "Access group management endpoints")
public class AccessGroupController {

    private final AccessGroupCommandService accessGroupCommandService;
    private final AccessGroupQueryService accessGroupQueryService;

    public AccessGroupController(AccessGroupCommandService accessGroupCommandService, AccessGroupQueryService accessGroupQueryService) {
        this.accessGroupCommandService = accessGroupCommandService;
        this.accessGroupQueryService = accessGroupQueryService;
    }

    @GetMapping
    @Operation(
        summary = "Get access groups by organization",
        description = "Returns all access groups belonging to a specific organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Access groups retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AccessGroupResource.class)
            )
        )
    })
    public ResponseEntity<List<AccessGroupResource>> getAccessGroupsByOrganization(@RequestParam Long organizationId) {
        var entities = accessGroupQueryService.handle(new GetAccessGroupsByOrganizationIdQuery(organizationId));
        var resources = entities.stream()
                .map(AccessGroupResourceFromPersistenceAssembler::toResourceFromPersistence)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    @Operation(
        summary = "Create access group",
        description = "Creates a new access group within an existing organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Access group created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AccessGroupResource.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Organization not found",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<?> createAccessGroup(@RequestBody CreateAccessGroupResource resource) {
        var command = CreateAccessGroupCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = accessGroupCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                AccessGroupResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }
}
