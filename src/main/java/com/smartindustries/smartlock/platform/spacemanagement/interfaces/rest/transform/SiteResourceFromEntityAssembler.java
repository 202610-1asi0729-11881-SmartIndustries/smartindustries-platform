package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Site;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.SiteResource;

public final class SiteResourceFromEntityAssembler {

    private SiteResourceFromEntityAssembler() {
    }

    public static SiteResource toResourceFromEntity(Site entity) {
        return new SiteResource(entity.getId(), entity.getOrganizationId(), entity.getName().value(), entity.getDescription());
    }
}
