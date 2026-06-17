package com.smartindustries.smartlock.platform.access.application.internal.queryservices;

import com.smartindustries.smartlock.platform.access.application.queryservices.AccessGroupQueryService;
import com.smartindustries.smartlock.platform.access.domain.model.queries.GetAccessGroupsByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.AccessGroupPersistenceEntity;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.repositories.AccessGroupPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessGroupQueryServiceImpl implements AccessGroupQueryService {

    private final AccessGroupPersistenceRepository persistenceRepository;

    public AccessGroupQueryServiceImpl(AccessGroupPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<AccessGroupPersistenceEntity> handle(GetAccessGroupsByOrganizationIdQuery query) {
        return persistenceRepository.findByOrganization_Id(query.organizationId());
    }
}
