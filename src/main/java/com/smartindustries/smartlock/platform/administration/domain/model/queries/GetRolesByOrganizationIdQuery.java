package com.smartindustries.smartlock.platform.administration.domain.model.queries;

public record GetRolesByOrganizationIdQuery(Long organizationId) {
    public GetRolesByOrganizationIdQuery {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
    }
}
