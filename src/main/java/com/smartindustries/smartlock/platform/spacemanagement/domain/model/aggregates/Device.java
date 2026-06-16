package com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates;

import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.ConnectDeviceToSiteCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.valueobjects.DeviceMode;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.valueobjects.DeviceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Device extends AbstractDomainAggregateRoot<Device> {

    @Setter
    private Long id;

    @Setter
    private Long siteId;

    @Setter
    private GenericName name;

    @Setter
    private DeviceStatus status;

    @Setter
    private DeviceMode mode;

    public Device() {
        this.status = DeviceStatus.ONLINE;
    }

    public Device(ConnectDeviceToSiteCommand command) {
        this();
        this.siteId = command.siteId();
        this.name = new GenericName(command.name());
        this.mode = DeviceMode.valueOf(command.mode().toUpperCase());
    }
}
