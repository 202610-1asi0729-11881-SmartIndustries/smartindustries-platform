package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdatePersonInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.UpdatePersonInformationResource;

public final class UpdatePersonInformationCommandFromResourceAssembler {

    private UpdatePersonInformationCommandFromResourceAssembler() {
    }

    public static UpdatePersonInformationCommand toCommandFromResource(UpdatePersonInformationResource resource, Long personId) {
        return new UpdatePersonInformationCommand(personId, resource.firstName(), resource.lastName(), resource.identityDocument());
    }
}
