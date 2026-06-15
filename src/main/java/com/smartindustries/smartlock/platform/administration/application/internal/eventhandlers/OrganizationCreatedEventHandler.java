package com.smartindustries.smartlock.platform.administration.application.internal.eventhandlers;

import com.smartindustries.smartlock.platform.administration.application.commandservices.RoleCommandService;
import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateBasicRoleCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateRootRoleCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.events.OrganizationCreatedIntegrationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service("administrationOrganizationCreatedEventHandler")
public class OrganizationCreatedEventHandler {

    private final RoleCommandService roleCommandService;

    public OrganizationCreatedEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void on(OrganizationCreatedIntegrationEvent event) {
        var rootResult = roleCommandService.handle(
                new CreateRootRoleCommand(event.organizationId(), event.creatorUserId()));
        if (rootResult instanceof Result.Failure<Role, ApplicationError>(ApplicationError error)) {
            log.warn("Failed to create root role for organization {}: {}",
                    event.organizationId(), error.message());
        }

        var basicResult = roleCommandService.handle(
                new CreateBasicRoleCommand(event.organizationId()));
        if (basicResult instanceof Result.Failure<Role, ApplicationError>(ApplicationError error)) {
            log.warn("Failed to create basic role for organization {}: {}",
                    event.organizationId(), error.message());
        }
    }
}
