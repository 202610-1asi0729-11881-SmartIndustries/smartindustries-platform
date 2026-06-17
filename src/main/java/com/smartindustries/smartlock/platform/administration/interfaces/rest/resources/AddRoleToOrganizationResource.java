package com.smartindustries.smartlock.platform.administration.interfaces.rest.resources;

public record AddRoleToOrganizationResource(Long organizationId, String name, boolean canCreateSites, boolean canCreatePeople, boolean canConnectDevices) {
}
