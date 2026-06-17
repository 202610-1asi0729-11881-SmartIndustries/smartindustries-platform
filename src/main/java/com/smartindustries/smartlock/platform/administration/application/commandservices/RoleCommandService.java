package com.smartindustries.smartlock.platform.administration.application.commandservices;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRoleToOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateBasicRoleCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateRootRoleCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;

public interface RoleCommandService {
    Result<Role, ApplicationError> handle(CreateRootRoleCommand command);
    Result<Role, ApplicationError> handle(CreateBasicRoleCommand command);
    Result<Role, ApplicationError> handle(AddRoleToOrganizationCommand command);
}
