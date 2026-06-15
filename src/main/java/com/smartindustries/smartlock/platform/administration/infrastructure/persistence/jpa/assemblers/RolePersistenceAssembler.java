package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.RolePersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;

public final class RolePersistenceAssembler {

    private RolePersistenceAssembler() {
    }

    public static Role toDomainFromPersistence(RolePersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Role();
        domain.setId(entity.getId());
        domain.setOrganizationId(entity.getOrganization().getId());
        domain.setName(entity.getName());
        domain.setPermissions(entity.getPermissions());
        domain.setDeletable(entity.isDeletable());
        return domain;
    }

    public static RolePersistenceEntity toPersistenceFromDomain(Role domain) {
        if (domain == null) return null;
        var entity = new RolePersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        if (domain.getOrganizationId() != null) {
            var orgRef = new OrganizationPersistenceEntity();
            orgRef.setId(domain.getOrganizationId());
            entity.setOrganization(orgRef);
        }
        entity.setName(domain.getName());
        entity.setPermissions(domain.getPermissions());
        entity.setDeletable(domain.isDeletable());
        return entity;
    }
}
