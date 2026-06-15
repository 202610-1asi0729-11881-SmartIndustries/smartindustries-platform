package com.smartindustries.smartlock.platform.shared.domain.model.valueobjects;

public record GenericName(String value) {

    public GenericName {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("GenericName must not be null or blank");
    }
}
