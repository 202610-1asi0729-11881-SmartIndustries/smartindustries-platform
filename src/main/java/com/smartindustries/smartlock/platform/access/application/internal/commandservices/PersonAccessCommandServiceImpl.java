package com.smartindustries.smartlock.platform.access.application.internal.commandservices;

import com.smartindustries.smartlock.platform.access.application.commandservices.PersonAccessCommandService;
import com.smartindustries.smartlock.platform.access.domain.model.aggregates.PersonAccess;
import com.smartindustries.smartlock.platform.access.domain.model.commands.CreatePersonAccessCommand;
import com.smartindustries.smartlock.platform.access.domain.model.valueobjects.PersonAccessStatus;
import com.smartindustries.smartlock.platform.access.domain.repositories.PersonAccessRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class PersonAccessCommandServiceImpl implements PersonAccessCommandService {

    private final PersonAccessRepository personAccessRepository;

    public PersonAccessCommandServiceImpl(PersonAccessRepository personAccessRepository) {
        this.personAccessRepository = personAccessRepository;
    }

    @Override
    public Result<PersonAccess, ApplicationError> handle(CreatePersonAccessCommand command) {
        try {
            var personAccess = new PersonAccess();
            personAccess.setPersonId(command.personId());
            personAccess.setStatus(PersonAccessStatus.ENABLED);
            var saved = personAccessRepository.save(personAccess);
            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("PersonAccess", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("person-access-creation", e.getMessage()));
        }
    }
}
