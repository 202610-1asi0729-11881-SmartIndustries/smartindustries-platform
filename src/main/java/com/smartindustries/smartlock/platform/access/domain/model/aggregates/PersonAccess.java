package com.smartindustries.smartlock.platform.access.domain.model.aggregates;

import com.smartindustries.smartlock.platform.access.domain.model.valueobjects.PersonAccessStatus;
import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PersonAccess extends AbstractDomainAggregateRoot<PersonAccess> {

    @Setter
    private Long id;

    @Setter
    private Long personId;

    @Setter
    private Long accessGroupId;

    @Setter
    private PersonAccessStatus status;

    public PersonAccess() {
    }
}
