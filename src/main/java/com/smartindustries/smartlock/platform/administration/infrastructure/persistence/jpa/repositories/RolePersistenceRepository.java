package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.RolePersistenceEntity;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePersistenceRepository extends JpaRepository<RolePersistenceEntity, Long> {
    List<RolePersistenceEntity> findByOrganization_Id(Long organizationId);
    boolean existsByOrganization_IdAndName(Long organizationId, GenericName name);
}
