package com.smartindustries.smartlock.platform.administration.interfaces.rest.resources;

public record UpdateRoleInformationResource(String name, boolean canCreateSites, boolean canCreatePeople, boolean canConnectDevices) {
}
