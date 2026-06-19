package com.smartindustries.smartlock.platform.spacemanagement.application.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Device;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.ConnectDeviceToSiteCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.DeleteDeviceCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateDeviceInformationCommand;

public interface DeviceCommandService {
    Result<Device, ApplicationError> handle(ConnectDeviceToSiteCommand command);
    Result<Device, ApplicationError> handle(UpdateDeviceInformationCommand command);
    Result<Device, ApplicationError> handle(DeleteDeviceCommand command);
}
