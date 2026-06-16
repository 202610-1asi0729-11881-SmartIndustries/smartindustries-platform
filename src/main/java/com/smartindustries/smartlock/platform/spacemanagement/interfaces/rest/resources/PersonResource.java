package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources;

public record PersonResource(Long id, Long organizationId, String firstName, String lastName, String identityDocument) {
}
