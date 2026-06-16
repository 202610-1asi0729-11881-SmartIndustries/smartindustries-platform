package com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries;

public record GetOrganizationsByUserIdQuery(Long userId) {
    public GetOrganizationsByUserIdQuery {
        if (userId == null || userId < 1)
            throw new IllegalArgumentException("userId cannot be null or less than 1");
    }
}
