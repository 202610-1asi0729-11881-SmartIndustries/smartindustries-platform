package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.administration.domain.repositories.RoleRepository;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.assemblers.RolePersistenceAssembler;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.repositories.RolePersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final RolePersistenceRepository jpaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public RoleRepositoryImpl(RolePersistenceRepository jpaRepository, ApplicationEventPublisher eventPublisher) {
        this.jpaRepository = jpaRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRepository.findById(id)
                .map(RolePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Role save(Role role, Long creatorUserId) {
        boolean isNew = role.getId() == null;
        var entity = RolePersistenceAssembler.toPersistenceFromDomain(role);
        var saved = jpaRepository.save(entity);
        var result = RolePersistenceAssembler.toDomainFromPersistence(saved);

        if (isNew) {
            result.onCreateRoot(creatorUserId);
            result.domainEvents().forEach(eventPublisher::publishEvent);
            result.clearDomainEvents();
        }
        return result;
    }
}
