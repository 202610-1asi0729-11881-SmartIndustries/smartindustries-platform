package com.smartindustries.smartlock.platform.access.domain.repositories;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.AccessGroup;

import java.util.Optional;

public interface AccessGroupRepository {
    Optional<AccessGroup> findById(Long id);
    AccessGroup save(AccessGroup accessGroup);
}
