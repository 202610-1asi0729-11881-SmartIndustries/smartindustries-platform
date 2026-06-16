package com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries;

public record GetDevicesByOrganizationIdQuery(Long organizationId) {
    public GetDevicesByOrganizationIdQuery {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
    }
}
