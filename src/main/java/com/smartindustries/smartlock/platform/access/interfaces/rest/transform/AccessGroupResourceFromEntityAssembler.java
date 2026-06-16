package com.smartindustries.smartlock.platform.access.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.AccessGroup;
import com.smartindustries.smartlock.platform.access.interfaces.rest.resources.AccessGroupResource;

public final class AccessGroupResourceFromEntityAssembler {

    private AccessGroupResourceFromEntityAssembler() {
    }

    public static AccessGroupResource toResourceFromEntity(AccessGroup entity) {
        return new AccessGroupResource(entity.getId(), entity.getOrganizationId(), entity.getName().value(), entity.getDescription());
    }
}
