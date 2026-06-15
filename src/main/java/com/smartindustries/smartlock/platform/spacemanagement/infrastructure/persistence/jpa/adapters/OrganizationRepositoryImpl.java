package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Organization;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.OrganizationRepository;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers.OrganizationPersistenceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.OrganizationPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private final OrganizationPersistenceRepository jpaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public OrganizationRepositoryImpl(OrganizationPersistenceRepository jpaRepository, ApplicationEventPublisher eventPublisher) {
        this.jpaRepository = jpaRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Organization> findById(Long id) {
        return jpaRepository.findById(id)
                .map(OrganizationPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Organization save(Organization organization) {
        boolean isNew = organization.getId() == null;
        var persistenceEntity = OrganizationPersistenceAssembler.toPersistenceFromDomain(organization);
        var saved = jpaRepository.save(persistenceEntity);
        var result = OrganizationPersistenceAssembler.toDomainFromPersistence(saved);

        if (isNew) {
            result.onCreate();
            result.domainEvents().forEach(eventPublisher::publishEvent);
            result.clearDomainEvents();
        }
        return result;
    }

    @Override
    public boolean existsByName(GenericName name) {
        return jpaRepository.existsByName(name);
    }
}
