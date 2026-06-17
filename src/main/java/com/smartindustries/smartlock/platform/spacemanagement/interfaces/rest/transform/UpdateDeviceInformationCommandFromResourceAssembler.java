package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateDeviceInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.UpdateDeviceInformationResource;

public final class UpdateDeviceInformationCommandFromResourceAssembler {

    private UpdateDeviceInformationCommandFromResourceAssembler() {
    }

    public static UpdateDeviceInformationCommand toCommandFromResource(UpdateDeviceInformationResource resource, Long deviceId) {
        return new UpdateDeviceInformationCommand(deviceId, resource.siteId(), resource.name(), resource.mode());
    }
}
