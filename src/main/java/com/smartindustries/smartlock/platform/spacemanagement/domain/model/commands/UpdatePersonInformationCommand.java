package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record UpdatePersonInformationCommand(Long personId, String firstName, String lastName, String identityDocument) {
    public UpdatePersonInformationCommand {
        if (personId == null || personId < 1)
            throw new IllegalArgumentException("personId cannot be null or less than 1");
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("firstName cannot be null or blank");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("lastName cannot be null or blank");
        if (identityDocument == null || identityDocument.isBlank())
            throw new IllegalArgumentException("identityDocument cannot be null or blank");
    }
}
