package com.smartindustries.smartlock.platform.spacemanagement.application.internal.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.PersonCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Person;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddPersonToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.OrganizationRepository;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonCommandServiceImpl implements PersonCommandService {

    private final PersonRepository personRepository;
    private final OrganizationRepository organizationRepository;

    public PersonCommandServiceImpl(PersonRepository personRepository, OrganizationRepository organizationRepository) {
        this.personRepository = personRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Result<Person, ApplicationError> handle(AddPersonToOrganizationCommand command) {
        try {
            if (organizationRepository.findById(command.organizationId()).isEmpty()) {
                return Result.failure(ApplicationError.notFound("Organization", command.organizationId().toString()));
            }

            var person = new Person(command);
            var savedPerson = personRepository.save(person);
            return Result.success(savedPerson);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Person", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("person-creation", e.getMessage()));
        }
    }
}
