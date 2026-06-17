package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.PersonAccessPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAccessPersistenceRepository extends JpaRepository<PersonAccessPersistenceEntity, Long> {
}
