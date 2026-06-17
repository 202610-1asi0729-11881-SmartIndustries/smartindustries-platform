package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateOrganizationInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.UpdateOrganizationInformationResource;

public final class UpdateOrganizationInformationCommandFromResourceAssembler {

    private UpdateOrganizationInformationCommandFromResourceAssembler() {
    }

    public static UpdateOrganizationInformationCommand toCommandFromResource(UpdateOrganizationInformationResource resource, Long organizationId) {
        return new UpdateOrganizationInformationCommand(organizationId, resource.name(), resource.description());
    }
}
