package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Person;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.PersonRepository;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers.PersonPersistenceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.PersonPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonPersistenceRepository jpaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public PersonRepositoryImpl(PersonPersistenceRepository jpaRepository, ApplicationEventPublisher eventPublisher) {
        this.jpaRepository = jpaRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return jpaRepository.findById(id)
                .map(PersonPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Person save(Person person) {
        boolean isNew = person.getId() == null;
        var entity = PersonPersistenceAssembler.toPersistenceFromDomain(person);
        var saved = jpaRepository.save(entity);
        var result = PersonPersistenceAssembler.toDomainFromPersistence(saved);

        if (isNew) {
            result.onAddedToOrganization();
            result.domainEvents().forEach(eventPublisher::publishEvent);
            result.clearDomainEvents();
        }
        return result;
    }
}
