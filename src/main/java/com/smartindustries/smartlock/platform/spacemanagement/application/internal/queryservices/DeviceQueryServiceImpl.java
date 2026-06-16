package com.smartindustries.smartlock.platform.spacemanagement.application.internal.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.DeviceQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetDevicesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.DevicePersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.DevicePersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceQueryServiceImpl implements DeviceQueryService {

    private final DevicePersistenceRepository persistenceRepository;

    public DeviceQueryServiceImpl(DevicePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<DevicePersistenceEntity> handle(GetDevicesByOrganizationIdQuery query) {
        return persistenceRepository.findBySite_Organization_Id(query.organizationId());
    }
}
