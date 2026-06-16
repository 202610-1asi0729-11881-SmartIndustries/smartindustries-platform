package com.smartindustries.smartlock.platform.access.domain.model.aggregates;

import com.smartindustries.smartlock.platform.access.domain.model.commands.CreateAccessGroupCommand;
import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AccessGroup extends AbstractDomainAggregateRoot<AccessGroup> {

    @Setter
    private Long id;

    @Setter
    private Long organizationId;

    @Setter
    private GenericName name;

    @Setter
    private String description;

    public AccessGroup() {
    }

    public AccessGroup(CreateAccessGroupCommand command) {
        this.organizationId = command.organizationId();
        this.name = new GenericName(command.name());
        this.description = command.description();
    }
}
