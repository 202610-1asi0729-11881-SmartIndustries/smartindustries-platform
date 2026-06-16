package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.RolePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePersistenceRepository extends JpaRepository<RolePersistenceEntity, Long> {
    List<RolePersistenceEntity> findByOrganization_Id(Long organizationId);
}
