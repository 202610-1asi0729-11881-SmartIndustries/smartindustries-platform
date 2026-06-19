package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.SiteCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.SiteQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Site;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.DeleteSiteCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetSitesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.AddSiteToOrganizationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.SiteResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.UpdateSiteInformationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.AddSiteToOrganizationCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.SiteResourceFromEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.SiteResourceFromPersistenceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.UpdateSiteInformationCommandFromResourceAssembler;
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

    @PutMapping("/{siteId}")
    @Operation(
        summary = "Update site information",
        description = "Updates the name and description of an existing site."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Site updated successfully",
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
            description = "Site not found",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<?> updateSite(@PathVariable Long siteId, @RequestBody UpdateSiteInformationResource resource) {
        var command = UpdateSiteInformationCommandFromResourceAssembler.toCommandFromResource(resource, siteId);
        var result = siteCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SiteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }

    @DeleteMapping("/{siteId}")
    @Operation(
        summary = "Delete site",
        description = "Deletes a site."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Site deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Site not found"
        )
    })
    public ResponseEntity<?> deleteSite(@PathVariable Long siteId) {
        var result = siteCommandService.handle(new DeleteSiteCommand(siteId));
        if (result instanceof Result.Success<Site, ApplicationError>) {
            return ResponseEntity.noContent().build();
        }
        return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                ((Result.Failure<Site, ApplicationError>) result).error());
    }
}
