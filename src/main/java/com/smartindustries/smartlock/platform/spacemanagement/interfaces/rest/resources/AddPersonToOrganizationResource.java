package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources;

public record AddPersonToOrganizationResource(Long organizationId, String firstName, String lastName, String identityDocument) {
}
