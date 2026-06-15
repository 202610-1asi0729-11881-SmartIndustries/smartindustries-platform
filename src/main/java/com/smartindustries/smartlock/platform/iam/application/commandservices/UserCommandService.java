package com.smartindustries.smartlock.platform.iam.application.commandservices;

import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.domain.model.commands.SignUpCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;

public interface UserCommandService {
    Result<User, ApplicationError> handle(SignUpCommand command);
}
