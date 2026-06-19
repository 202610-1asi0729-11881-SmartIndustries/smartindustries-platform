package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record DeleteSiteCommand(Long siteId) {
    public DeleteSiteCommand {
        if (siteId == null || siteId < 1)
            throw new IllegalArgumentException("siteId cannot be null or less than 1");
    }
}
