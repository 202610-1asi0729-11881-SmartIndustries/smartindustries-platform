package com.smartindustries.smartlock.platform.access.interfaces.rest.resources;

public record CreateAccessGroupResource(Long organizationId, String name, String description) {
}
