package com.smartindustries.smartlock.platform.spacemanagement.application.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Site;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddSiteToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateSiteInformationCommand;

public interface SiteCommandService {
    Result<Site, ApplicationError> handle(AddSiteToOrganizationCommand command);
    Result<Site, ApplicationError> handle(UpdateSiteInformationCommand command);
}
