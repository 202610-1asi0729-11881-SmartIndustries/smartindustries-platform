package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.MembershipPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipPersistenceRepository extends JpaRepository<MembershipPersistenceEntity, Long> {
    List<MembershipPersistenceEntity> findByRole_Organization_Id(Long organizationId);
}
