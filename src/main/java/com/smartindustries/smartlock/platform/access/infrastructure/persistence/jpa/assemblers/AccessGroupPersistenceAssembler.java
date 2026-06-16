package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.AccessGroup;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.AccessGroupPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;

public final class AccessGroupPersistenceAssembler {

    private AccessGroupPersistenceAssembler() {
    }

    public static AccessGroup toDomainFromPersistence(AccessGroupPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new AccessGroup();
        domain.setId(entity.getId());
        domain.setOrganizationId(entity.getOrganization().getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        return domain;
    }

    public static AccessGroupPersistenceEntity toPersistenceFromDomain(AccessGroup domain) {
        if (domain == null) return null;
        var entity = new AccessGroupPersistenceEntity();
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
