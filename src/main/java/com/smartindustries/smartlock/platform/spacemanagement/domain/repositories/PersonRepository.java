package com.smartindustries.smartlock.platform.spacemanagement.domain.repositories;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findById(Long id);
    Person save(Person person);
}
