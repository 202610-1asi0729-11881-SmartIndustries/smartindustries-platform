package com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersistenceRepository extends JpaRepository<UserPersistenceEntity, Long> {

    boolean existsByEmail(Email email);
}
