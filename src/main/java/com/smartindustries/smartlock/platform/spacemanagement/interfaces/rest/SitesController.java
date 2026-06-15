package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest;

import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.SiteCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.AddSiteToOrganizationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.SiteResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.AddSiteToOrganizationCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.SiteResourceFromEntityAssembler;
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

@RestController
@RequestMapping(value = "/api/v1/sites", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sites", description = "Site management endpoints")
public class SitesController {

    private final SiteCommandService siteCommandService;

    public SitesController(SiteCommandService siteCommandService) {
        this.siteCommandService = siteCommandService;
    }

    @PostMapping
    @Operation(
        summary = "Add site to organization",
        description = "Creates a new site within an existing organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Site created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SiteResource.class)
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
    public ResponseEntity<?> addSite(@RequestBody AddSiteToOrganizationResource resource) {
        var command = AddSiteToOrganizationCommandFromResourceAssembler.toCommandFromResource(resource, resource.organizationId());
        var result = siteCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SiteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }
}
