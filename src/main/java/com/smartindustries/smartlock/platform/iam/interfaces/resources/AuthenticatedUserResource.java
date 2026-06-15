package com.smartindustries.smartlock.platform.iam.interfaces.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "AuthenticatedUserResponse",
    description = "Authenticated user information with JWT token",
    example = "{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"token\": \"eyJhbGciOiJIUzI1NiIs...\"}"
)
public record AuthenticatedUserResource(
    @Schema(description = "User unique identifier", example = "1")
    Long id,

    @Schema(description = "User first name", example = "John")
    String firstName,

    String lastName,

    @Schema(description = "JWT Bearer token for authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String token
) {
}
