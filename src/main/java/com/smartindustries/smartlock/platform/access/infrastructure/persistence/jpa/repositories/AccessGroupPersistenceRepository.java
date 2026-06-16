package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.AccessGroupPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessGroupPersistenceRepository extends JpaRepository<AccessGroupPersistenceEntity, Long> {
}
