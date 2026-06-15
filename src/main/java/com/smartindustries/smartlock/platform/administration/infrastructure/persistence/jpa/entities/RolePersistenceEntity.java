package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities;

import com.smartindustries.smartlock.platform.administration.domain.model.valueobjects.RolePermissions;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.converters.GenericNamePersistenceConverter;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class RolePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationPersistenceEntity organization;

    @Convert(converter = GenericNamePersistenceConverter.class)
    @Column(name = "name", nullable = false, length = 100)
    private GenericName name;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "canCreateSites", column = @Column(name = "can_create_sites", nullable = false)),
        @AttributeOverride(name = "canCreatePeople", column = @Column(name = "can_create_people", nullable = false)),
        @AttributeOverride(name = "canConnectDevices", column = @Column(name = "can_connect_devices", nullable = false))
    })
    private RolePermissions permissions;

    @Column(name = "deletable", nullable = false)
    private boolean deletable;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<MembershipPersistenceEntity> memberships = new ArrayList<>();
}
