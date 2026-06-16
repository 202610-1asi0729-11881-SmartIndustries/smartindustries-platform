package com.smartindustries.smartlock.platform.access.application.commandservices;

import com.smartindustries.smartlock.platform.access.domain.model.aggregates.AccessGroup;
import com.smartindustries.smartlock.platform.access.domain.model.commands.CreateAccessGroupCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;

public interface AccessGroupCommandService {
    Result<AccessGroup, ApplicationError> handle(CreateAccessGroupCommand command);
}
