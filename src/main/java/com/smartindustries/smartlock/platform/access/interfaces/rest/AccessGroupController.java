package com.smartindustries.smartlock.platform.access.interfaces.rest;

import com.smartindustries.smartlock.platform.access.application.commandservices.AccessGroupCommandService;
import com.smartindustries.smartlock.platform.access.interfaces.rest.resources.AccessGroupResource;
import com.smartindustries.smartlock.platform.access.interfaces.rest.resources.CreateAccessGroupResource;
import com.smartindustries.smartlock.platform.access.interfaces.rest.transform.AccessGroupResourceFromEntityAssembler;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/access-groups", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Access Groups", description = "Access group management endpoints")
public class AccessGroupController {

    private final AccessGroupCommandService accessGroupCommandService;

    public AccessGroupController(AccessGroupCommandService accessGroupCommandService) {
        this.accessGroupCommandService = accessGroupCommandService;
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
