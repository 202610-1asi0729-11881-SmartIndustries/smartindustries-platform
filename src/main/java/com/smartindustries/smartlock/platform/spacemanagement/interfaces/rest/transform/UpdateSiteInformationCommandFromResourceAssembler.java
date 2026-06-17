package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateSiteInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.UpdateSiteInformationResource;

public final class UpdateSiteInformationCommandFromResourceAssembler {

    private UpdateSiteInformationCommandFromResourceAssembler() {
    }

    public static UpdateSiteInformationCommand toCommandFromResource(UpdateSiteInformationResource resource, Long siteId) {
        return new UpdateSiteInformationCommand(siteId, resource.name(), resource.description());
    }
}
