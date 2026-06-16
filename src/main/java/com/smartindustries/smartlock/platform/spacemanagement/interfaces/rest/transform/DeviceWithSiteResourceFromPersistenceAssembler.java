package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.DevicePersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.DeviceWithSiteResource;

public final class DeviceWithSiteResourceFromPersistenceAssembler {

    private DeviceWithSiteResourceFromPersistenceAssembler() {
    }

    public static DeviceWithSiteResource toResourceFromPersistence(DevicePersistenceEntity entity) {
        var site = entity.getSite();
        return new DeviceWithSiteResource(
                entity.getId(),
                site.getId(),
                site.getName().value(),
                site.getDescription(),
                entity.getName().value(),
                entity.getStatus().name(),
                entity.getMode().name());
    }
}
