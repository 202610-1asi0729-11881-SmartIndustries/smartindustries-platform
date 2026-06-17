package com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates;

import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddSiteToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateSiteInformationCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Site extends AbstractDomainAggregateRoot<Site> {

    @Setter
    private Long id;

    @Setter
    private Long organizationId;

    @Setter
    private GenericName name;

    @Setter
    private String description;

    public Site() {
    }

    public Site(AddSiteToOrganizationCommand command) {
        this.organizationId = command.organizationId();
        this.name = new GenericName(command.name());
        this.description = command.description();
    }

    public void updateInformation(UpdateSiteInformationCommand command) {
        this.name = new GenericName(command.name());
        this.description = command.description();
    }
}
