package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest;

import com.smartindustries.smartlock.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.DeviceCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.ConnectDeviceToSiteResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.DeviceResource;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.ConnectDeviceToSiteCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Devices", description = "Device management endpoints")
public class DeviceController {

    private final DeviceCommandService deviceCommandService;

    public DeviceController(DeviceCommandService deviceCommandService) {
        this.deviceCommandService = deviceCommandService;
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
}
