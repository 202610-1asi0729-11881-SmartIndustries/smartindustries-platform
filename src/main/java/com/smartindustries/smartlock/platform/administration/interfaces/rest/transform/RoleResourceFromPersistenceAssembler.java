package com.smartindustries.smartlock.platform.administration.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.RolePersistenceEntity;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.RoleResource;

public final class RoleResourceFromPersistenceAssembler {

    private RoleResourceFromPersistenceAssembler() {
    }

    public static RoleResource toResourceFromPersistence(RolePersistenceEntity entity) {
        return new RoleResource(
                entity.getId(),
                entity.getOrganization().getId(),
                entity.getName().value(),
                entity.getPermissions().canCreateSites(),
                entity.getPermissions().canCreatePeople(),
                entity.getPermissions().canConnectDevices(),
                entity.isDeletable());
    }
}
