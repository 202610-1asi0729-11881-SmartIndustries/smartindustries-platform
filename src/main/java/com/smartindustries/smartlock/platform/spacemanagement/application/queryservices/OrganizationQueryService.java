package com.smartindustries.smartlock.platform.spacemanagement.application.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetOrganizationsByUserIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;

import java.util.List;

public interface OrganizationQueryService {
    List<OrganizationPersistenceEntity> handle(GetOrganizationsByUserIdQuery query);
}
