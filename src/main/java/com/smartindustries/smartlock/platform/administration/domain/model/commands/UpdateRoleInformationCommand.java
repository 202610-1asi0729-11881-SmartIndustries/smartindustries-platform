package com.smartindustries.smartlock.platform.administration.domain.model.commands;

public record UpdateRoleInformationCommand(Long roleId, String name, boolean canCreateSites, boolean canCreatePeople, boolean canConnectDevices) {
    public UpdateRoleInformationCommand {
        if (roleId == null || roleId < 1)
            throw new IllegalArgumentException("roleId cannot be null or less than 1");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or blank");
    }
}
