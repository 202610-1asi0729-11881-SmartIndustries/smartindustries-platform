package com.smartindustries.smartlock.platform.spacemanagement.application.internal.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.PeopleQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetPeopleByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.PersonPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleQueryServiceImpl implements PeopleQueryService {

    private final PersonPersistenceRepository persistenceRepository;

    public PeopleQueryServiceImpl(PersonPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<PersonPersistenceEntity> handle(GetPeopleByOrganizationIdQuery query) {
        return persistenceRepository.findByOrganization_Id(query.organizationId());
    }
}
