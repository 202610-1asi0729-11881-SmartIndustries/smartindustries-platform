package com.smartindustries.smartlock.platform.iam.interfaces.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "SignInRequest",
    description = "User sign-in request with credentials",
    example = "{\"email\": \"john.doe@example.com\", \"password\": \"SecurePass123!\"}"
)
public record SignInResource(

    String email,

    @Schema(
        description = "User password",
        example = "SecurePass123!",
        minLength = 8,
        maxLength = 64
    )
    String password
) {
}
