package com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.entities.ScheduleDayPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDayPersistenceRepository extends JpaRepository<ScheduleDayPersistenceEntity, Long> {
}
