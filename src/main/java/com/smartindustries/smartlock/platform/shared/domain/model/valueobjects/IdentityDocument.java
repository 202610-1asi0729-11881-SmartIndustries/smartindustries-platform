package com.smartindustries.smartlock.platform.shared.domain.model.valueobjects;

import java.util.regex.Pattern;

public record IdentityDocument(String value) {

    private static final Pattern PATTERN = Pattern.compile("^[0-9]{8}$");

    public IdentityDocument {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Identity document must not be null or blank");
        if (!PATTERN.matcher(value).matches())
            throw new IllegalArgumentException("Identity document must be exactly 8 digits");
    }
}
