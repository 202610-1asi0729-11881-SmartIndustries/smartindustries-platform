package com.smartindustries.smartlock.platform.spacemanagement.application.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetPeopleByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;

import java.util.List;

public interface PeopleQueryService {
    List<PersonPersistenceEntity> handle(GetPeopleByOrganizationIdQuery query);
}
