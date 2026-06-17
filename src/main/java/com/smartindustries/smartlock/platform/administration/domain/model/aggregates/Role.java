package com.smartindustries.smartlock.platform.administration.domain.model.aggregates;

import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRoleToOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.events.RootRoleCreatedEvent;
import com.smartindustries.smartlock.platform.administration.domain.model.valueobjects.RolePermissions;
import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Role extends AbstractDomainAggregateRoot<Role> {

    @Setter
    private Long id;

    @Setter
    private Long organizationId;

    @Setter
    private GenericName name;

    @Setter
    private RolePermissions permissions;

    @Setter
    private boolean deletable;

    public Role() {
    }

    public static Role createRoot(Long organizationId) {
        var role = new Role();
        role.organizationId = organizationId;
        role.name = new GenericName("Root Admin");
        role.permissions = new RolePermissions(true, true, true);
        role.deletable = false;
        return role;
    }

    public static Role createBasic(Long organizationId) {
        var role = new Role();
        role.organizationId = organizationId;
        role.name = new GenericName("Basic");
        role.permissions = new RolePermissions(false, false, false);
        role.deletable = false;
        return role;
    }

    public static Role create(AddRoleToOrganizationCommand command) {
        var role = new Role();
        role.organizationId = command.organizationId();
        role.name = new GenericName(command.name());
        role.permissions = new RolePermissions(command.canCreateSites(), command.canCreatePeople(), command.canConnectDevices());
        role.deletable = true;
        return role;
    }

    public void onCreateRoot(Long creatorUserId) {
        this.registerDomainEvent(new RootRoleCreatedEvent(this, this.getId(), this.organizationId, creatorUserId));
    }
}
