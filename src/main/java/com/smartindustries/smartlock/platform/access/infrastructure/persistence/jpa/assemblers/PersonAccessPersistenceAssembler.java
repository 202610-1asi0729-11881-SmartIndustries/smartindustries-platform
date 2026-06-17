package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.PersonAccess;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.AccessGroupPersistenceEntity;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.PersonAccessPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;

public final class PersonAccessPersistenceAssembler {

    private PersonAccessPersistenceAssembler() {
    }

    public static PersonAccess toDomainFromPersistence(PersonAccessPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new PersonAccess();
        domain.setId(entity.getId());
        domain.setPersonId(entity.getPerson().getId());
        domain.setAccessGroupId(entity.getAccessGroup() != null ? entity.getAccessGroup().getId() : null);
        domain.setStatus(entity.getStatus());
        return domain;
    }

    public static PersonAccessPersistenceEntity toPersistenceFromDomain(PersonAccess domain) {
        if (domain == null) return null;
        var entity = new PersonAccessPersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        if (domain.getPersonId() != null) {
            var personRef = new PersonPersistenceEntity();
            personRef.setId(domain.getPersonId());
            entity.setPerson(personRef);
        }
        if (domain.getAccessGroupId() != null) {
            var accessGroupRef = new AccessGroupPersistenceEntity();
            accessGroupRef.setId(domain.getAccessGroupId());
            entity.setAccessGroup(accessGroupRef);
        }
        entity.setStatus(domain.getStatus());
        return entity;
    }
}
