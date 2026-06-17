package com.smartindustries.smartlock.platform.access.application.internal.eventhandlers;

import com.smartindustries.smartlock.platform.access.application.commandservices.PersonAccessCommandService;
import com.smartindustries.smartlock.platform.access.domain.model.aggregates.PersonAccess;
import com.smartindustries.smartlock.platform.access.domain.model.commands.CreatePersonAccessCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.events.PersonAddedToOrganizationIntegrationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service("accessPersonAddedToOrganizationEventHandler")
public class PersonAddedToOrganizationEventHandler {

    private final PersonAccessCommandService personAccessCommandService;

    public PersonAddedToOrganizationEventHandler(PersonAccessCommandService personAccessCommandService) {
        this.personAccessCommandService = personAccessCommandService;
    }

    @EventListener
    public void on(PersonAddedToOrganizationIntegrationEvent event) {
        var command = new CreatePersonAccessCommand(event.personId());
        var result = personAccessCommandService.handle(command);

        if (result instanceof Result.Failure<PersonAccess, ApplicationError> f) {
            log.warn("Failed to create person access for person {}: {}",
                    event.personId(), f.error().message());
        }
    }
}
