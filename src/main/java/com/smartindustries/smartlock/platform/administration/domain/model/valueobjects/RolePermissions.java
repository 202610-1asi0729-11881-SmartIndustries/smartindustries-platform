package com.smartindustries.smartlock.platform.administration.domain.model.valueobjects;

public record RolePermissions(boolean canCreateSites, boolean canCreatePeople, boolean canConnectDevices) {
}
