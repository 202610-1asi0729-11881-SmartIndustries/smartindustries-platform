package com.smartindustries.smartlock.platform.iam.interfaces.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "UserResponse",
    description = "User information response",
    example = "{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\"}"
)
public record UserResource(
    @Schema(description = "User unique identifier", example = "1")
    Long id,

    @Schema(description = "User first name", example = "John")
    String firstName,

    String lastName,

    String email

) {
}
