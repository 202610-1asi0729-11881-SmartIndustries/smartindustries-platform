package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Person;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.PersonResource;

public final class PersonResourceFromEntityAssembler {

    private PersonResourceFromEntityAssembler() {
    }

    public static PersonResource toResourceFromEntity(Person entity) {
        return new PersonResource(entity.getId(), entity.getOrganizationId(), entity.getName().firstName(), entity.getName().lastName(), entity.getIdentityDocument().value());
    }
}
