package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest;

import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.DeviceCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.DeviceQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetDevicesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.ConnectDeviceToSiteResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.DeviceResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.DeviceWithSiteResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.UpdateDeviceInformationResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.ConnectDeviceToSiteCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.DeviceWithSiteResourceFromPersistenceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.UpdateDeviceInformationCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Devices", description = "Device management endpoints")
public class DeviceController {

    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;

    public DeviceController(DeviceCommandService deviceCommandService, DeviceQueryService deviceQueryService) {
        this.deviceCommandService = deviceCommandService;
        this.deviceQueryService = deviceQueryService;
    }

    @GetMapping
    @Operation(
        summary = "Get devices by organization",
        description = "Returns all devices belonging to a specific organization, including their site information."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Devices retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DeviceWithSiteResource.class)
            )
        )
    })
    public ResponseEntity<List<DeviceWithSiteResource>> getDevicesByOrganization(@RequestParam Long organizationId) {
        var entities = deviceQueryService.handle(new GetDevicesByOrganizationIdQuery(organizationId));
        var resources = entities.stream()
                .map(DeviceWithSiteResourceFromPersistenceAssembler::toResourceFromPersistence)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    @Operation(
        summary = "Connect device to site",
        description = "Connects a new device to an existing site."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Device connected successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DeviceResource.class)
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
    public ResponseEntity<?> connectDevice(@RequestBody ConnectDeviceToSiteResource resource) {
        var command = ConnectDeviceToSiteCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = deviceCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DeviceResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }

    @PutMapping("/{deviceId}")
    @Operation(
        summary = "Update device information",
        description = "Updates the site, name and mode of an existing device."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Device updated successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DeviceResource.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Device or site not found",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<?> updateDevice(@PathVariable Long deviceId, @RequestBody UpdateDeviceInformationResource resource) {
        var command = UpdateDeviceInformationCommandFromResourceAssembler.toCommandFromResource(resource, deviceId);
        var result = deviceCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DeviceResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK);
    }
}
