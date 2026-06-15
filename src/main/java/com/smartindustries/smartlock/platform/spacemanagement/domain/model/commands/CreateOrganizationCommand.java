package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record CreateOrganizationCommand(String name, String description, Long creatorUserId) {
}
