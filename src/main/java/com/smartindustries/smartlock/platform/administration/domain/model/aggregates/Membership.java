package com.smartindustries.smartlock.platform.administration.domain.model.aggregates;

import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRootUserToOrganizationCommand;
import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Membership extends AbstractDomainAggregateRoot<Membership> {

    @Setter
    private Long id;

    @Setter
    private Long userId;

    @Setter
    private Long roleId;

    public Membership() {
    }

    public Membership(AddRootUserToOrganizationCommand command) {
        this.userId = command.userId();
        this.roleId = command.roleId();
    }

    public void updateRole(Long newRoleId) {
        this.roleId = newRoleId;
    }
}
