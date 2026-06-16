package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest;

import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.PersonCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.PeopleQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetPeopleByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.AddPersonToOrganizationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.PersonResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.AddPersonToOrganizationCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.PersonResourceFromEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.PersonResourceFromPersistenceAssembler;
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
@RequestMapping(value = "/api/v1/people", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "People", description = "People management endpoints")
public class PersonController {

    private final PersonCommandService personCommandService;
    private final PeopleQueryService peopleQueryService;

    public PersonController(PersonCommandService personCommandService, PeopleQueryService peopleQueryService) {
        this.personCommandService = personCommandService;
        this.peopleQueryService = peopleQueryService;
    }

    @GetMapping
    @Operation(
        summary = "Get people by organization",
        description = "Returns all people belonging to a specific organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "People retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PersonResource.class)
            )
        )
    })
    public ResponseEntity<List<PersonResource>> getPeopleByOrganization(@RequestParam Long organizationId) {
        var entities = peopleQueryService.handle(new GetPeopleByOrganizationIdQuery(organizationId));
        var resources = entities.stream()
                .map(PersonResourceFromPersistenceAssembler::toResourceFromPersistence)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    @Operation(
        summary = "Add person to organization",
        description = "Creates a new person within an existing organization."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Person created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PersonResource.class)
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
    public ResponseEntity<?> addPerson(@RequestBody AddPersonToOrganizationResource resource) {
        var command = AddPersonToOrganizationCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = personCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                PersonResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }
}
