package com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.report.domain.model.aggregates.ScheduleDay;
import com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.entities.ScheduleDayPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;

public final class ScheduleDayPersistenceAssembler {

    private ScheduleDayPersistenceAssembler() {
    }

    public static ScheduleDay toDomainFromPersistence(ScheduleDayPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new ScheduleDay();
        domain.setId(entity.getId());
        domain.setPersonId(entity.getPerson().getId());
        domain.setDay(entity.getDay());
        domain.setTimeBlock(entity.getTimeBlock());
        return domain;
    }

    public static ScheduleDayPersistenceEntity toPersistenceFromDomain(ScheduleDay domain) {
        if (domain == null) return null;
        var entity = new ScheduleDayPersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        if (domain.getPersonId() != null) {
            var personRef = new PersonPersistenceEntity();
            personRef.setId(domain.getPersonId());
            entity.setPerson(personRef);
        }
        entity.setDay(domain.getDay());
        entity.setTimeBlock(domain.getTimeBlock());
        return entity;
    }
}
