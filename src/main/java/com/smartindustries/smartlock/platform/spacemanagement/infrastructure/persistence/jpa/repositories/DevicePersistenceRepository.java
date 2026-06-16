package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.DevicePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePersistenceRepository extends JpaRepository<DevicePersistenceEntity, Long> {
}
