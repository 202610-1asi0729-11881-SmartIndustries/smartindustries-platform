package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record AddSiteToOrganizationCommand(Long organizationId, String name, String description) {
    public AddSiteToOrganizationCommand {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or blank");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be null or blank");
    }
}
