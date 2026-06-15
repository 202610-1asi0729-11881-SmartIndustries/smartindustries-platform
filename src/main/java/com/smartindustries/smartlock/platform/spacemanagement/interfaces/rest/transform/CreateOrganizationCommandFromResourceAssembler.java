package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.CreateOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.CreateOrganizationResource;

public final class CreateOrganizationCommandFromResourceAssembler {

    private CreateOrganizationCommandFromResourceAssembler() {
    }

    public static CreateOrganizationCommand toCommandFromResource(CreateOrganizationResource resource, Long creatorUserId) {
        return new CreateOrganizationCommand(resource.name(), resource.description(), creatorUserId);
    }
}
