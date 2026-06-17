package com.smartindustries.smartlock.platform.spacemanagement.application.internal.eventhandlers;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.events.PersonAddedToOrganizationEvent;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.events.PersonAddedToOrganizationIntegrationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service("spacemanagementPersonAddedToOrganizationEventHandler")
public class PersonAddedToOrganizationEventHandler {

    private final ApplicationEventPublisher eventPublisher;

    public PersonAddedToOrganizationEventHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void on(PersonAddedToOrganizationEvent event) {
        var integrationEvent = new PersonAddedToOrganizationIntegrationEvent(
                event.getPersonId());
        eventPublisher.publishEvent(integrationEvent);
    }
}
