package com.smartindustries.smartlock.platform.access.domain.repositories;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.PersonAccess;

import java.util.Optional;

public interface PersonAccessRepository {
    Optional<PersonAccess> findById(Long id);
    PersonAccess save(PersonAccess personAccess);
}
