package com.smartindustries.smartlock.platform.spacemanagement.application.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Organization;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.CreateOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateOrganizationInformationCommand;

public interface OrganizationCommandService {
    Result<Organization, ApplicationError> handle(CreateOrganizationCommand command);
    Result<Organization, ApplicationError> handle(UpdateOrganizationInformationCommand command);
}
