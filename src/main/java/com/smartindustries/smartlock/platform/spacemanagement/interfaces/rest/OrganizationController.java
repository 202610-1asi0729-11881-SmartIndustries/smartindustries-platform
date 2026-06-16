package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.OrganizationCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.application.internal.outboundservices.acl.ExternalIamService;
import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.OrganizationQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetOrganizationsByUserIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.CreateOrganizationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.OrganizationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.OrganizationSummaryResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.CreateOrganizationCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.OrganizationResourceFromEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.OrganizationSummaryResourceFromPersistenceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/organizations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Organizations", description = "Organization management endpoints")
public class OrganizationController {

    private final OrganizationCommandService organizationCommandService;
    private final OrganizationQueryService organizationQueryService;
    private final ExternalIamService externalIamService;

    public OrganizationController(OrganizationCommandService organizationCommandService, OrganizationQueryService organizationQueryService, ExternalIamService externalIamService) {
        this.organizationCommandService = organizationCommandService;
        this.organizationQueryService = organizationQueryService;
        this.externalIamService = externalIamService;
    }

    @PostMapping
    @Operation(
        summary = "Create organization",
        description = "Creates a new organization. The authenticated user becomes the root admin."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Organization created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OrganizationResource.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Conflict - organization name already exists",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<?> createOrganization(@RequestBody CreateOrganizationResource resource) {
        var email = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        var userId = externalIamService.fetchUserIdByEmail(email);

        if (userId.isEmpty()) {
            var error = ApplicationError.validationError("User", "Could not find user for authentication token");
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(error);
        }

        var command = CreateOrganizationCommandFromResourceAssembler.toCommandFromResource(resource, userId.get());
        var result = organizationCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                OrganizationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
        summary = "Get my organizations",
        description = "Returns all organizations where the authenticated user is a member."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Organizations retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OrganizationSummaryResource.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Could not resolve authenticated user",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<List<OrganizationSummaryResource>> getMyOrganizations() {
        var email = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        var userId = externalIamService.fetchUserIdByEmail(email);

        if (userId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var organizations = organizationQueryService.handle(new GetOrganizationsByUserIdQuery(userId.get()));
        var resources = organizations.stream()
                .map(OrganizationSummaryResourceFromPersistenceAssembler::toResourceFromPersistence)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
