package com.smartindustries.smartlock.platform.administration.application.internal.queryservices;

import com.smartindustries.smartlock.platform.administration.application.queryservices.RoleQueryService;
import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetRolesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.RolePersistenceEntity;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.repositories.RolePersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    private final RolePersistenceRepository persistenceRepository;

    public RoleQueryServiceImpl(RolePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<RolePersistenceEntity> handle(GetRolesByOrganizationIdQuery query) {
        return persistenceRepository.findByOrganization_Id(query.organizationId());
    }
}
