package com.smartindustries.smartlock.platform.access.application.queryservices;

import com.smartindustries.smartlock.platform.access.domain.model.queries.GetAccessGroupsByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.AccessGroupPersistenceEntity;

import java.util.List;

public interface AccessGroupQueryService {
    List<AccessGroupPersistenceEntity> handle(GetAccessGroupsByOrganizationIdQuery query);
}
