package com.smartindustries.smartlock.platform.spacemanagement.interfaces.events;

public record OrganizationCreatedIntegrationEvent(
        Long organizationId,
        String name,
        Long creatorUserId) {
}
