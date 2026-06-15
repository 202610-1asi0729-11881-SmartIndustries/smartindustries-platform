package com.smartindustries.smartlock.platform.iam.interfaces.transform;

import com.smartindustries.smartlock.platform.iam.domain.model.commands.SignUpCommand;
import com.smartindustries.smartlock.platform.iam.interfaces.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    /**
     * Converts the incoming sign-up resource to an application command.
     *
     * @param resource sign-up payload from REST API
     * @return sign-up command consumed by the application layer
     */
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.firstName(), resource.lastName(), resource.email(), resource.password());
    }
}
