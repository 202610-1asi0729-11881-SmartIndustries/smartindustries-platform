package com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.repositories;

import com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPersistenceRepository extends JpaRepository<UserPersistenceEntity, Long> {

    Optional<UserPersistenceEntity> findByEmail(Email email);

    boolean existsByEmail(Email email);
}
