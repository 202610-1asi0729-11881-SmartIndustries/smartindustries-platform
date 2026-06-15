package com.smartindustries.smartlock.platform.iam.interfaces.transform;

import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.interfaces.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    /**
     * Creates a resource from the authenticated {@link User} aggregate and issued bearer token.
     *
     * @param user authenticated user aggregate
     * @param token generated bearer token
     * @return resource used by the authentication endpoint response
     */
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getName().firstName(), user.getName().lastName(), token);
    }
}
