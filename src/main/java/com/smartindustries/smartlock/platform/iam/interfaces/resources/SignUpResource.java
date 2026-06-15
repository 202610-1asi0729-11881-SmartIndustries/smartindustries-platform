package com.smartindustries.smartlock.platform.iam.interfaces.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Resource received to register a new IAM user.
 */
@Schema(
    name = "SignUpRequest",
    description = "User sign-up request with credentials",
    example = "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\", \"password\": \"SecurePass123!\"}"
)
public record SignUpResource(
    @Schema(
        description = "User first name",
        example = "John",
        minLength = 1,
        maxLength = 50
    )
    String firstName,

    String lastName,

    String email,

    @Schema(
        description = "User password (minimum 8 characters)",
        example = "SecurePass123!",
        minLength = 8,
        maxLength = 255
    )
    String password

) {
}
