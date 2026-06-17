package com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates;

import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.FullName;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.IdentityDocument;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddPersonToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdatePersonInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.events.PersonAddedToOrganizationEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Person extends AbstractDomainAggregateRoot<Person> {

    @Setter
    private Long id;

    @Setter
    private Long organizationId;

    @Setter
    private FullName name;

    @Setter
    private IdentityDocument identityDocument;

    @Setter
    private Long accessGroupId;

    public Person() {
    }

    public Person(AddPersonToOrganizationCommand command) {
        this.organizationId = command.organizationId();
        this.name = new FullName(command.firstName(), command.lastName());
        this.identityDocument = new IdentityDocument(command.identityDocument());
    }

    public void onAddedToOrganization() {
        this.registerDomainEvent(new PersonAddedToOrganizationEvent(this, this.getId(), this.getName().getFullName(), this.getIdentityDocument().value()));
    }

    public void updateInformation(UpdatePersonInformationCommand command) {
        this.name = new FullName(command.firstName(), command.lastName());
        this.identityDocument = new IdentityDocument(command.identityDocument());
    }
}
