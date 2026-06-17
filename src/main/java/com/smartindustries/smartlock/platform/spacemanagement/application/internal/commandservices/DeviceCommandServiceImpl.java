package com.smartindustries.smartlock.platform.spacemanagement.application.internal.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.DeviceCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Device;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.ConnectDeviceToSiteCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateDeviceInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.DeviceRepository;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.SiteRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceCommandServiceImpl implements DeviceCommandService {

    private final DeviceRepository deviceRepository;
    private final SiteRepository siteRepository;

    public DeviceCommandServiceImpl(DeviceRepository deviceRepository, SiteRepository siteRepository) {
        this.deviceRepository = deviceRepository;
        this.siteRepository = siteRepository;
    }

    @Override
    public Result<Device, ApplicationError> handle(ConnectDeviceToSiteCommand command) {
        try {
            if (siteRepository.findById(command.siteId()).isEmpty()) {
                return Result.failure(ApplicationError.notFound("Site", command.siteId().toString()));
            }

            var device = new Device(command);
            var savedDevice = deviceRepository.save(device);
            return Result.success(savedDevice);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Device", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("device-connection", e.getMessage()));
        }
    }

    @Override
    public Result<Device, ApplicationError> handle(UpdateDeviceInformationCommand command) {
        try {
            var device = deviceRepository.findById(command.deviceId());
            if (device.isEmpty()) {
                return Result.failure(ApplicationError.notFound("Device", command.deviceId().toString()));
            }

            if (siteRepository.findById(command.siteId()).isEmpty()) {
                return Result.failure(ApplicationError.notFound("Site", command.siteId().toString()));
            }

            device.get().updateInformation(command);
            var saved = deviceRepository.save(device.get());
            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Device", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("device-update", e.getMessage()));
        }
    }
}
