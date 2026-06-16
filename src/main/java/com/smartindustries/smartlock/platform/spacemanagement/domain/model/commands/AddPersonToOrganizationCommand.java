package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record AddPersonToOrganizationCommand(Long organizationId, String firstName, String lastName, String identityDocument) {
    public AddPersonToOrganizationCommand {
        if (organizationId == null || organizationId < 1)
            throw new IllegalArgumentException("organizationId cannot be null or less than 1");
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("firstName cannot be null or blank");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("lastName cannot be null or blank");
        if (identityDocument == null || identityDocument.isBlank())
            throw new IllegalArgumentException("identityDocument cannot be null or blank");
    }
}
