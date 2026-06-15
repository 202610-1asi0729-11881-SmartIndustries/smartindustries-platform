package com.smartindustries.smartlock.platform.iam.interfaces.transform;

import com.smartindustries.smartlock.platform.iam.domain.model.commands.SignInCommand;
import com.smartindustries.smartlock.platform.iam.interfaces.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    /**
     * Converts the incoming sign-in resource to an application command.
     *
     * @param signInResource sign-in payload from REST API
     * @return sign-in command consumed by the application layer
     */
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
