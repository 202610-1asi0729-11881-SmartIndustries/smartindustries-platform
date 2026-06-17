package com.smartindustries.smartlock.platform.access.domain.model.queries;

public record GetAccessGroupsByOrganizationIdQuery(Long organizationId) {
    public GetAccessGroupsByOrganizationIdQuery {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
    }
}
