package com.smartindustries.smartlock.platform.spacemanagement.application.internal.eventhandlers;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.events.OrganizationCreatedEvent;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.events.OrganizationCreatedIntegrationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service("spaceManagementOrganizationCreatedEventHandler")
public class OrganizationCreatedEventHandler {

    private final ApplicationEventPublisher eventPublisher;

    public OrganizationCreatedEventHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void on(OrganizationCreatedEvent event) {
        var integrationEvent = new OrganizationCreatedIntegrationEvent(
                event.getOrganizationId(),
                event.getName(),
                event.getCreatorUserId());
        eventPublisher.publishEvent(integrationEvent);
    }
}
