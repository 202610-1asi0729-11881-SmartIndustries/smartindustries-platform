package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonPersistenceRepository extends JpaRepository<PersonPersistenceEntity, Long> {
    List<PersonPersistenceEntity> findByOrganization_Id(Long organizationId);
}
