package com.smartindustries.smartlock.platform.administration.domain.model.queries;

public record GetUsersByOrganizationIdQuery(Long organizationId) {
    public GetUsersByOrganizationIdQuery {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
    }
}
