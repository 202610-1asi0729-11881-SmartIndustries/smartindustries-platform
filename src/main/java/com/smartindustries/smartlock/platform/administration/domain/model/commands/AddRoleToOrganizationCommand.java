package com.smartindustries.smartlock.platform.administration.domain.model.commands;

public record AddRoleToOrganizationCommand(Long organizationId, String name, boolean canCreateSites, boolean canCreatePeople, boolean canConnectDevices) {
    public AddRoleToOrganizationCommand {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or blank");
    }
}
