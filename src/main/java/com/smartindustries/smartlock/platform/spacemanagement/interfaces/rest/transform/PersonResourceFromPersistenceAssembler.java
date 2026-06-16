package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.PersonResource;

public final class PersonResourceFromPersistenceAssembler {

    private PersonResourceFromPersistenceAssembler() {
    }

    public static PersonResource toResourceFromPersistence(PersonPersistenceEntity entity) {
        return new PersonResource(
                entity.getId(),
                entity.getOrganization().getId(),
                entity.getName().firstName(),
                entity.getName().lastName(),
                entity.getIdentityDocument().value());
    }
}
