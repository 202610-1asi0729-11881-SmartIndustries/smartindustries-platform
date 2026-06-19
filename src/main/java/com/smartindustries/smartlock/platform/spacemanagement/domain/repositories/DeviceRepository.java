package com.smartindustries.smartlock.platform.spacemanagement.domain.repositories;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Device;

import java.util.Optional;

public interface DeviceRepository {
    Optional<Device> findById(Long id);
    Device save(Device device);
    void deleteById(Long id);
}
