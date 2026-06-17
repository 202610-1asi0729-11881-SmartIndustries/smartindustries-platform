package com.smartindustries.smartlock.platform.administration.domain.repositories;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(Long id);
    Role save(Role role, Long creatorUserId);
    boolean existsByOrganizationIdAndName(Long organizationId, GenericName name);
}
