package com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands;

public record DeleteDeviceCommand(Long deviceId) {
    public DeleteDeviceCommand {
        if (deviceId == null || deviceId < 1)
            throw new IllegalArgumentException("deviceId cannot be null or less than 1");
    }
}
