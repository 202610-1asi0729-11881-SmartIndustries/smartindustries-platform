package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationPersistenceRepository extends JpaRepository<OrganizationPersistenceEntity, Long> {
    boolean existsByName(GenericName name);

    @Query("SELECT DISTINCT o FROM OrganizationPersistenceEntity o " +
           "JOIN o.roles r " +
           "JOIN r.memberships m " +
           "WHERE m.user.id = :userId")
    List<OrganizationPersistenceEntity> findByUserId(@Param("userId") Long userId);
}
