package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record UpdateDeviceInformationCommand(Long deviceId, Long siteId, String name, String mode) {
    public UpdateDeviceInformationCommand {
        if (deviceId == null || deviceId < 1)
            throw new IllegalArgumentException("deviceId cannot be null or less than 1");
        if (siteId == null || siteId < 1)
            throw new IllegalArgumentException("siteId cannot be null or less than 1");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or blank");
        if (mode == null || mode.isBlank())
            throw new IllegalArgumentException("mode cannot be null or blank");
    }
}
