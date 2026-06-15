package com.smartindustries.smartlock.platform.administration.application.internal.eventhandlers;

import com.smartindustries.smartlock.platform.administration.application.commandservices.MembershipCommandService;
import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Membership;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRootUserToOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.events.RootRoleCreatedEvent;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RootRoleCreatedEventHandler {

    private final MembershipCommandService membershipCommandService;

    public RootRoleCreatedEventHandler(MembershipCommandService membershipCommandService) {
        this.membershipCommandService = membershipCommandService;
    }

    @EventListener
    public void on(RootRoleCreatedEvent event) {
        var command = new AddRootUserToOrganizationCommand(event.getCreatorUserId(), event.getRoleId());
        var result = membershipCommandService.handle(command);

        if (result instanceof Result.Failure<Membership, ApplicationError> f) {
            log.warn("Failed to add root user to organization for role {}: {}",
                    event.getRoleId(), f.error().message());
        }
    }
}
