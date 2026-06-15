package com.smartindustries.smartlock.platform.shared.domain.model.valueobjects;

public record Password(String value) {

    public Password {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Password must not be null or blank");
        if (value.length() < 8 || value.length() > 64)
            throw new IllegalArgumentException("Password must be between 8 and 64 characters");
    }
}
