package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Organization;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.OrganizationResource;

public final class OrganizationResourceFromEntityAssembler {

    private OrganizationResourceFromEntityAssembler() {
    }

    public static OrganizationResource toResourceFromEntity(Organization entity) {
        return new OrganizationResource(entity.getId(), entity.getName().value(), entity.getDescription());
    }
}
