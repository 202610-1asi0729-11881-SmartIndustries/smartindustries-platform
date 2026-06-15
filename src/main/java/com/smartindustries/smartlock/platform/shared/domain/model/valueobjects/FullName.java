package com.smartindustries.smartlock.platform.shared.domain.model.valueobjects;

public record FullName(String firstName, String lastName) {

    public FullName {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("firstName must not be null or blank");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("lastName must not be null or blank");
    }

    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }
}
