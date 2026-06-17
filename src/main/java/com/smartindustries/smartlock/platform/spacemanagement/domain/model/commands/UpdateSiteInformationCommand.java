package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record UpdateSiteInformationCommand(Long siteId, String name, String description) {
    public UpdateSiteInformationCommand {
        if (siteId == null || siteId < 1)
            throw new IllegalArgumentException("siteId cannot be null or less than 1");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or blank");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be null or blank");
    }
}
