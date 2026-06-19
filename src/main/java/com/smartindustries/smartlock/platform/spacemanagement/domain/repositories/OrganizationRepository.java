package com.smartindustries.smartlock.platform.spacemanagement.domain.repositories;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Organization;

import java.util.Optional;

public interface OrganizationRepository {
    Optional<Organization> findById(Long id);
    Organization save(Organization organization, Long creatorUserId);
    boolean existsByName(GenericName name);
    void deleteById(Long id);
}
