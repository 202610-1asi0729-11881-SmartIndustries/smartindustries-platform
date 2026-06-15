package com.smartindustries.smartlock.platform.administration.domain.model.commands;

public record AddRootUserToOrganizationCommand(Long userId, Long roleId) {
    public AddRootUserToOrganizationCommand {
        if (userId == null || userId < 1)
            throw new IllegalArgumentException("userId cannot be null or less than 1");
        if (roleId == null || roleId < 1)
            throw new IllegalArgumentException("roleId cannot be null or less than 1");
    }
}
