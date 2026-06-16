package com.smartindustries.smartlock.platform.spacemanagement.application.internal.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.OrganizationQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetOrganizationsByUserIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.OrganizationPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationQueryServiceImpl implements OrganizationQueryService {

    private final OrganizationPersistenceRepository persistenceRepository;

    public OrganizationQueryServiceImpl(OrganizationPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<OrganizationPersistenceEntity> handle(GetOrganizationsByUserIdQuery query) {
        return persistenceRepository.findByUserId(query.userId());
    }
}
