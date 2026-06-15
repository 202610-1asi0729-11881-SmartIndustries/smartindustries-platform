package com.smartindustries.smartlock.platform.shared.domain.model.valueobjects;

import java.util.regex.Pattern;

public record Email(String address) {

    private static final Pattern PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public Email {
        if (address == null || address.isBlank())
            throw new IllegalArgumentException("Email must not be null or blank");
        if (!PATTERN.matcher(address).matches())
            throw new IllegalArgumentException("Email must be a valid format");
    }
}
