package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record DeletePersonCommand(Long personId) {
    public DeletePersonCommand {
        if (personId == null || personId < 1)
            throw new IllegalArgumentException("personId cannot be null or less than 1");
    }
}
