package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record DeleteOrganizationCommand(Long organizationId) {
    public DeleteOrganizationCommand {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
    }
}
