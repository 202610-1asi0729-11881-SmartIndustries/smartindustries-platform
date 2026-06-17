package com.smartindustries.smartlock.platform.spacemanagement.application.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Person;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddPersonToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdatePersonInformationCommand;

public interface PersonCommandService {
    Result<Person, ApplicationError> handle(AddPersonToOrganizationCommand command);
    Result<Person, ApplicationError> handle(UpdatePersonInformationCommand command);
}
