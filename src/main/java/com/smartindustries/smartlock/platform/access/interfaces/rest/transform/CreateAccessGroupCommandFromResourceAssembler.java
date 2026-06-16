package com.smartindustries.smartlock.platform.access.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.access.domain.model.commands.CreateAccessGroupCommand;
import com.smartindustries.smartlock.platform.access.interfaces.rest.resources.CreateAccessGroupResource;

public final class CreateAccessGroupCommandFromResourceAssembler {

    private CreateAccessGroupCommandFromResourceAssembler() {
    }

    public static CreateAccessGroupCommand toCommandFromResource(CreateAccessGroupResource resource) {
        return new CreateAccessGroupCommand(resource.organizationId(), resource.name(), resource.description());
    }
}
