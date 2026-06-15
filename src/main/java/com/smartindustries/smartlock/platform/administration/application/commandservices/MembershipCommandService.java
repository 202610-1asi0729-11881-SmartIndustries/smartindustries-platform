package com.smartindustries.smartlock.platform.administration.application.commandservices;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Membership;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRootUserToOrganizationCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;

public interface MembershipCommandService {
    Result<Membership, ApplicationError> handle(AddRootUserToOrganizationCommand command);
}
