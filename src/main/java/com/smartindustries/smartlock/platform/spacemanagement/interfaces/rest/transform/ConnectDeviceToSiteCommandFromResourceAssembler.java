package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.ConnectDeviceToSiteCommand;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.ConnectDeviceToSiteResource;

public final class ConnectDeviceToSiteCommandFromResourceAssembler {

    private ConnectDeviceToSiteCommandFromResourceAssembler() {
    }

    public static ConnectDeviceToSiteCommand toCommandFromResource(ConnectDeviceToSiteResource resource) {
        return new ConnectDeviceToSiteCommand(resource.siteId(), resource.name(), resource.mode());
    }
}
