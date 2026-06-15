package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Site;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.SitePersistenceEntity;

public final class SitePersistenceAssembler {

    private SitePersistenceAssembler() {
    }

    public static Site toDomainFromPersistence(SitePersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Site();
        domain.setId(entity.getId());
        domain.setOrganizationId(entity.getOrganization().getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public static SitePersistenceEntity toPersistenceFromDomain(Site domain) {
        if (domain == null) return null;
        var entity = new SitePersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        if (domain.getOrganizationId() != null) {
            var orgRef = new OrganizationPersistenceEntity();
            orgRef.setId(domain.getOrganizationId());
            entity.setOrganization(orgRef);
        }
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}
