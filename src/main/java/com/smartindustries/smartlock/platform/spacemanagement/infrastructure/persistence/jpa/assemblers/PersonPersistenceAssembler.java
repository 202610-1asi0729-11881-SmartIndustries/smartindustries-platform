package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Person;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;

public final class PersonPersistenceAssembler {

    private PersonPersistenceAssembler() {
    }

    public static Person toDomainFromPersistence(PersonPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Person();
        domain.setId(entity.getId());
        domain.setOrganizationId(entity.getOrganization().getId());
        domain.setName(entity.getName());
        domain.setIdentityDocument(entity.getIdentityDocument());
        return domain;
    }

    public static PersonPersistenceEntity toPersistenceFromDomain(Person domain) {
        if (domain == null) return null;
        var entity = new PersonPersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        if (domain.getOrganizationId() != null) {
            var orgRef = new OrganizationPersistenceEntity();
            orgRef.setId(domain.getOrganizationId());
            entity.setOrganization(orgRef);
        }
        entity.setName(domain.getName());
        entity.setIdentityDocument(domain.getIdentityDocument());
        return entity;
    }
}
