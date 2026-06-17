package com.smartindustries.smartlock.platform.access.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.AccessGroupPersistenceEntity;
import com.smartindustries.smartlock.platform.access.interfaces.rest.resources.AccessGroupResource;

public final class AccessGroupResourceFromPersistenceAssembler {

    private AccessGroupResourceFromPersistenceAssembler() {
    }

    public static AccessGroupResource toResourceFromPersistence(AccessGroupPersistenceEntity entity) {
        return new AccessGroupResource(
                entity.getId(),
                entity.getOrganization().getId(),
                entity.getName().value(),
                entity.getDescription());
    }
}
