package com.smartindustries.smartlock.platform.administration.application.queryservices;

import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetRolesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.RolePersistenceEntity;

import java.util.List;

public interface RoleQueryService {
    List<RolePersistenceEntity> handle(GetRolesByOrganizationIdQuery query);
}
