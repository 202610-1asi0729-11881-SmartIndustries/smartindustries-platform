package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Device;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.DevicePersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.SitePersistenceEntity;

public final class DevicePersistenceAssembler {

    private DevicePersistenceAssembler() {
    }

    public static Device toDomainFromPersistence(DevicePersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Device();
        domain.setId(entity.getId());
        domain.setSiteId(entity.getSite().getId());
        domain.setName(entity.getName());
        domain.setStatus(entity.getStatus());
        domain.setMode(entity.getMode());
        return domain;
    }

    public static DevicePersistenceEntity toPersistenceFromDomain(Device domain) {
        if (domain == null) return null;
        var entity = new DevicePersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        if (domain.getSiteId() != null) {
            var siteRef = new SitePersistenceEntity();
            siteRef.setId(domain.getSiteId());
            entity.setSite(siteRef);
        }
        entity.setName(domain.getName());
        entity.setStatus(domain.getStatus());
        entity.setMode(domain.getMode());
        return entity;
    }
}
