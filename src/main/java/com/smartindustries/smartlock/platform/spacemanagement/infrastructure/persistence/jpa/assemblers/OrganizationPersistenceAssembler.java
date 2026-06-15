package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Organization;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;

public final class OrganizationPersistenceAssembler {

    private OrganizationPersistenceAssembler() {
    }

    public static Organization toDomainFromPersistence(OrganizationPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Organization();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public static OrganizationPersistenceEntity toPersistenceFromDomain(Organization domain) {
        if (domain == null) return null;
        var entity = new OrganizationPersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}
