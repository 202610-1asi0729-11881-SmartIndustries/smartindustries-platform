package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.converters.GenericNamePersistenceConverter;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "access_groups")
@Getter
@Setter
@NoArgsConstructor
public class AccessGroupPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationPersistenceEntity organization;

    @Convert(converter = GenericNamePersistenceConverter.class)
    @Column(name = "name", nullable = false, length = 100)
    private GenericName name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "accessGroup", fetch = FetchType.LAZY)
    private List<PersonPersistenceEntity> people = new ArrayList<>();
}
