package com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates;

import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.CreateOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.events.OrganizationCreatedEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Organization extends AbstractDomainAggregateRoot<Organization> {

    @Setter
    private Long id;

    @Setter
    private GenericName name;

    @Setter
    private String description;

    public Organization() {
    }

    public Organization(CreateOrganizationCommand command) {
        this.name = new GenericName(command.name());
        this.description = command.description();
    }

    public void onCreate(Long creatorUserId) {
        this.registerDomainEvent(new OrganizationCreatedEvent(this, this.getId(), this.name.value(), creatorUserId));
    }
}
