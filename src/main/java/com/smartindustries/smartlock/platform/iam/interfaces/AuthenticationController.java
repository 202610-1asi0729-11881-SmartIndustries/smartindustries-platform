package com.smartindustries.smartlock.platform.iam.interfaces;

import com.smartindustries.smartlock.platform.iam.application.commandservices.UserCommandService;
import com.smartindustries.smartlock.platform.iam.interfaces.resources.SignUpResource;
import com.smartindustries.smartlock.platform.iam.interfaces.resources.UserResource;
import com.smartindustries.smartlock.platform.iam.interfaces.transform.SignUpCommandFromResourceAssembler;
import com.smartindustries.smartlock.platform.iam.interfaces.transform.UserResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication and user registration endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body with email and password.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    @Operation(
        summary = "User registration",
        description = "Creates a new user account with provided credentials."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "User created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserResource.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data or email already exists",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Conflict - email already taken",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<?> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var result = userCommandService.handle(signUpCommand);
        return ResponseEntityAssembler.toResponseEntityFromResult(
            result,
            UserResourceFromEntityAssembler::toResourceFromEntity,
            HttpStatus.CREATED
        );

    }
}
