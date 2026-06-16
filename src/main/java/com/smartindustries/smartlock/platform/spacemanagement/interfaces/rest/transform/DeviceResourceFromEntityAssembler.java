package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Device;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.DeviceResource;

public final class DeviceResourceFromEntityAssembler {

    private DeviceResourceFromEntityAssembler() {
    }

    public static DeviceResource toResourceFromEntity(Device entity) {
        return new DeviceResource(entity.getId(), entity.getSiteId(), entity.getName().value(), entity.getStatus().name(), entity.getMode().name());
    }
}
