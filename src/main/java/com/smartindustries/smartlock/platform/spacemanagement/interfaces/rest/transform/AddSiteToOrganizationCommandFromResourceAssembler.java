package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddSiteToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.AddSiteToOrganizationResource;

public final class AddSiteToOrganizationCommandFromResourceAssembler {

    private AddSiteToOrganizationCommandFromResourceAssembler() {
    }

    public static AddSiteToOrganizationCommand toCommandFromResource(AddSiteToOrganizationResource resource, Long organizationId) {
        return new AddSiteToOrganizationCommand(organizationId, resource.name(), resource.description());
    }
}
