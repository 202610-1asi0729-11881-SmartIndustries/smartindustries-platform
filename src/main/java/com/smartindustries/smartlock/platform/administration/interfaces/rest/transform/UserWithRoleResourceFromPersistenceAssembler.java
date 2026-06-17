package com.smartindustries.smartlock.platform.administration.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.MembershipPersistenceEntity;
import com.smartindustries.smartlock.platform.administration.interfaces.rest.resources.UserWithRoleResource;

public final class UserWithRoleResourceFromPersistenceAssembler {

    private UserWithRoleResourceFromPersistenceAssembler() {
    }

    public static UserWithRoleResource toResourceFromPersistence(MembershipPersistenceEntity entity) {
        var user = entity.getUser();
        var role = entity.getRole();
        return new UserWithRoleResource(
                user.getId(),
                user.getEmail().address(),
                user.getName().firstName(),
                user.getName().lastName(),
                role.getId(),
                role.getName().value());
    }
}
