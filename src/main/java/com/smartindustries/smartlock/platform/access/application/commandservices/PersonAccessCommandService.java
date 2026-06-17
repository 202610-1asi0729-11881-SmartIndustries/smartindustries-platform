package com.smartindustries.smartlock.platform.access.application.commandservices;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.PersonAccess;
import com.smartindustries.smartlock.platform.access.domain.model.commands.CreatePersonAccessCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;

public interface PersonAccessCommandService {
    Result<PersonAccess, ApplicationError> handle(CreatePersonAccessCommand command);
}
