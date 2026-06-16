package com.smartindustries.smartlock.platform.spacemanagement.application.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetDevicesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.DevicePersistenceEntity;

import java.util.List;

public interface DeviceQueryService {
    List<DevicePersistenceEntity> handle(GetDevicesByOrganizationIdQuery query);
}
