package com.smartindustries.smartlock.platform.administration.domain.model.commands;

public record CreateBasicRoleCommand(Long organizationId) {
    public CreateBasicRoleCommand {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
    }
}
