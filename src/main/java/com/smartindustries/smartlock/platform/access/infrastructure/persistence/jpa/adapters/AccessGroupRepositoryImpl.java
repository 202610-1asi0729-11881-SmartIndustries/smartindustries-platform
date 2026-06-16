package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.AccessGroup;
import com.smartindustries.smartlock.platform.access.domain.repositories.AccessGroupRepository;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.assemblers.AccessGroupPersistenceAssembler;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.repositories.AccessGroupPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccessGroupRepositoryImpl implements AccessGroupRepository {

    private final AccessGroupPersistenceRepository jpaRepository;

    public AccessGroupRepositoryImpl(AccessGroupPersistenceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<AccessGroup> findById(Long id) {
        return jpaRepository.findById(id)
                .map(AccessGroupPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public AccessGroup save(AccessGroup accessGroup) {
        var entity = AccessGroupPersistenceAssembler.toPersistenceFromDomain(accessGroup);
        var saved = jpaRepository.save(entity);
        return AccessGroupPersistenceAssembler.toDomainFromPersistence(saved);
    }
}
