package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Device;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.DeviceRepository;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers.DevicePersistenceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.DevicePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    private final DevicePersistenceRepository jpaRepository;

    public DeviceRepositoryImpl(DevicePersistenceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Device> findById(Long id) {
        return jpaRepository.findById(id)
                .map(DevicePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Device save(Device device) {
        var entity = DevicePersistenceAssembler.toPersistenceFromDomain(device);
        var saved = jpaRepository.save(entity);
        return DevicePersistenceAssembler.toDomainFromPersistence(saved);
    }
}
