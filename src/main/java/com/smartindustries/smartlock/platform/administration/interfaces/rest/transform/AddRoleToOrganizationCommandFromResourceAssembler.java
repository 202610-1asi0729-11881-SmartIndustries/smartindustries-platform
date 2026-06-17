package com.smartindustries.smartlock.platform.administration.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRoleToOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.AddRoleToOrganizationResource;

public final class AddRoleToOrganizationCommandFromResourceAssembler {

    private AddRoleToOrganizationCommandFromResourceAssembler() {
    }

    public static AddRoleToOrganizationCommand toCommandFromResource(AddRoleToOrganizationResource resource) {
        return new AddRoleToOrganizationCommand(resource.organizationId(), resource.name(), resource.canCreateSites(), resource.canCreatePeople(), resource.canConnectDevices());
    }
}
