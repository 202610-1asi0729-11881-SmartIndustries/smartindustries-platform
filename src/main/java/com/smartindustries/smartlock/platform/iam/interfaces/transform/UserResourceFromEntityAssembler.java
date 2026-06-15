package com.smartindustries.smartlock.platform.iam.interfaces.transform;

import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.interfaces.resources.UserResource;

public class UserResourceFromEntityAssembler {
    /**
     * Converts a user aggregate to its REST representation.
     *
     * @param user user aggregate
     * @return user resource
     */
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getName().firstName(), user.getName().lastName(), user.getEmail().address());
    }
}
