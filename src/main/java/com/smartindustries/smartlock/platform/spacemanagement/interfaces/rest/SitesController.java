package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest;

import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.SiteCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.SiteQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetSitesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.AddSiteToOrganizationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.SiteResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.AddSiteToOrganizationCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.SiteResourceFromEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.SiteResourceFromPersistenceAssembler;
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
@RequestMapping(value = "/api/v1/sites", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sites", description = "Site management endpoints")
public class SitesController {

    private final SiteCommandService siteCommandService;
    private final SiteQueryService siteQueryService;

    public SitesController(SiteCommandService siteCommandService, SiteQueryService siteQueryService) {
        this.siteCommandService = siteCommandService;
        this.siteQueryService = siteQueryService;
    }

    @GetMapping
    @Operation(
        summary = "Get sites by organization",
        description = "Returns all sites belonging to a specific organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sites retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SiteResource.class)
            )
        )
    })
    public ResponseEntity<List<SiteResource>> getSitesByOrganization(@RequestParam Long organizationId) {
        var entities = siteQueryService.handle(new GetSitesByOrganizationIdQuery(organizationId));
        var resources = entities.stream()
                .map(SiteResourceFromPersistenceAssembler::toResourceFromPersistence)
                .toList();
        return ResponseEntity.ok(resources);
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
