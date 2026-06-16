package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Person;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.PersonRepository;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers.PersonPersistenceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.PersonPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonPersistenceRepository jpaRepository;

    public PersonRepositoryImpl(PersonPersistenceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return jpaRepository.findById(id)
                .map(PersonPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Person save(Person person) {
        var entity = PersonPersistenceAssembler.toPersistenceFromDomain(person);
        var saved = jpaRepository.save(entity);
        return PersonPersistenceAssembler.toDomainFromPersistence(saved);
    }
}
