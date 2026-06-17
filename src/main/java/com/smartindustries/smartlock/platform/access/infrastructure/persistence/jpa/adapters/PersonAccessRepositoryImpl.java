package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.PersonAccess;
import com.smartindustries.smartlock.platform.access.domain.repositories.PersonAccessRepository;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.assemblers.PersonAccessPersistenceAssembler;
import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.repositories.PersonAccessPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonAccessRepositoryImpl implements PersonAccessRepository {

    private final PersonAccessPersistenceRepository jpaRepository;

    public PersonAccessRepositoryImpl(PersonAccessPersistenceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<PersonAccess> findById(Long id) {
        return jpaRepository.findById(id)
                .map(PersonAccessPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public PersonAccess save(PersonAccess personAccess) {
        var entity = PersonAccessPersistenceAssembler.toPersistenceFromDomain(personAccess);
        var saved = jpaRepository.save(entity);
        return PersonAccessPersistenceAssembler.toDomainFromPersistence(saved);
    }
}
