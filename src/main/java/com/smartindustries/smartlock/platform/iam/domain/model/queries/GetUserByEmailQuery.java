package com.smartindustries.smartlock.platform.iam.domain.model.queries;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;

public record GetUserByEmailQuery(Email email) {
    public GetUserByEmailQuery {
        if (email == null)
            throw new IllegalArgumentException("email cannot be null");
    }
}
