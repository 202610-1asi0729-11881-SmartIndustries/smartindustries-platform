package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddPersonToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.AddPersonToOrganizationResource;

public final class AddPersonToOrganizationCommandFromResourceAssembler {

    private AddPersonToOrganizationCommandFromResourceAssembler() {
    }

    public static AddPersonToOrganizationCommand toCommandFromResource(AddPersonToOrganizationResource resource) {
        return new AddPersonToOrganizationCommand(resource.organizationId(), resource.firstName(), resource.lastName(), resource.identityDocument());
    }
}
