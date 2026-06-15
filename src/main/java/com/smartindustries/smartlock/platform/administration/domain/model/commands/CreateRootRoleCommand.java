package com.smartindustries.smartlock.platform.administration.domain.model.commands;

public record CreateRootRoleCommand(Long organizationId, Long creatorUserId) {
    public CreateRootRoleCommand {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
        if (creatorUserId == null || creatorUserId < 1)
            throw new IllegalArgumentException("creatorUserId cannot be null or less than 1");
    }
}
