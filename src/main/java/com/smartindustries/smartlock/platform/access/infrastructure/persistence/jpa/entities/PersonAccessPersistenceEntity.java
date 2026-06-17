package com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities;

import com.smartindustries.smartlock.platform.access.domain.model.valueobjects.PersonAccessStatus;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_accesses")
@Getter
@Setter
@NoArgsConstructor
public class PersonAccessPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private PersonPersistenceEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_group_id")
    private AccessGroupPersistenceEntity accessGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private PersonAccessStatus status;
}
