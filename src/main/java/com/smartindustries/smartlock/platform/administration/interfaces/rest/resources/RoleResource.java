package com.smartindustries.smartlock.platform.administration.interfaces.rest.resources;

public record RoleResource(Long id, Long organizationId, String name, boolean canCreateSites, boolean canCreatePeople, boolean canConnectDevices, boolean deletable) {
}
