package com.smartindustries.smartlock.platform.administration.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.RoleResource;

public final class RoleResourceFromEntityAssembler {

    private RoleResourceFromEntityAssembler() {
    }

    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(
                entity.getId(),
                entity.getOrganizationId(),
                entity.getName().value(),
                entity.getPermissions().canCreateSites(),
                entity.getPermissions().canCreatePeople(),
                entity.getPermissions().canConnectDevices(),
                entity.isDeletable());
    }
}
