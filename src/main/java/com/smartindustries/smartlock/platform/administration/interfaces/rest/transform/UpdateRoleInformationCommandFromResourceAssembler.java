package com.smartindustries.smartlock.platform.administration.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.administration.domain.model.commands.UpdateRoleInformationCommand;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.UpdateRoleInformationResource;

public final class UpdateRoleInformationCommandFromResourceAssembler {

    private UpdateRoleInformationCommandFromResourceAssembler() {
    }

    public static UpdateRoleInformationCommand toCommandFromResource(UpdateRoleInformationResource resource, Long roleId) {
        return new UpdateRoleInformationCommand(roleId, resource.name(), resource.canCreateSites(), resource.canCreatePeople(), resource.canConnectDevices());
    }
}
