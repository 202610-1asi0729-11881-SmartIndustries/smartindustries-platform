package com.smartindustries.smartlock.platform.administration.domain.model.commands;

public record UpdateUserRoleInOrganizationCommand(Long userId, Long organizationId, Long newRoleId) {
    public UpdateUserRoleInOrganizationCommand {
        if (userId == null || userId < 1)
            throw new IllegalArgumentException("userId cannot be null or less than 1");
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
        if (newRoleId == null || newRoleId < 1)
            throw new IllegalArgumentException("newRoleId cannot be null or less than 1");
    }
}
