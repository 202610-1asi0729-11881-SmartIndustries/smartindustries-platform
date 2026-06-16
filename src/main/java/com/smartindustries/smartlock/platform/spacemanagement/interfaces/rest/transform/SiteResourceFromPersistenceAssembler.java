package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.SitePersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.SiteResource;

public final class SiteResourceFromPersistenceAssembler {

    private SiteResourceFromPersistenceAssembler() {
    }

    public static SiteResource toResourceFromPersistence(SitePersistenceEntity entity) {
        return new SiteResource(
                entity.getId(),
                entity.getOrganization().getId(),
                entity.getName().value(),
                entity.getDescription());
    }
}
