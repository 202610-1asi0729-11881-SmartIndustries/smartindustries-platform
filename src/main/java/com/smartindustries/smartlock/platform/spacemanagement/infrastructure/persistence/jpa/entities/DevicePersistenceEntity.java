package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.converters.GenericNamePersistenceConverter;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.valueobjects.DeviceMode;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.valueobjects.DeviceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
public class DevicePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private SitePersistenceEntity site;

    @Convert(converter = GenericNamePersistenceConverter.class)
    @Column(name = "name", nullable = false, length = 100)
    private GenericName name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private DeviceStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode", nullable = false, length = 50)
    private DeviceMode mode;
}
