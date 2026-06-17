package com.smartindustries.smartlock.platform.access.domain.model.commands;

public record CreatePersonAccessCommand(Long personId) {
    public CreatePersonAccessCommand {
        if (personId == null || personId < 1)
            throw new IllegalArgumentException("personId cannot be null or less than 1");
    }
}
