package com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.entities;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Day;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.TimeBlock;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.PersonPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedule_days")
@Getter
@Setter
@NoArgsConstructor
public class ScheduleDayPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonPersistenceEntity person;

    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false, length = 50)
    private Day day;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "start", column = @Column(name = "start")),
        @AttributeOverride(name = "end", column = @Column(name = "end"))
    })
    private TimeBlock timeBlock;
}
