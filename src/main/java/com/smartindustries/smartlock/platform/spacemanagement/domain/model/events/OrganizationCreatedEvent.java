package com.smartindustries.smartlock.platform.spacemanagement.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrganizationCreatedEvent extends ApplicationEvent {

    private final Long organizationId;
    private final String name;
    private final Long creatorUserId;

    public OrganizationCreatedEvent(Object source, Long organizationId, String name, Long creatorUserId) {
        super(source);
        this.organizationId = organizationId;
        this.name = name;
        this.creatorUserId = creatorUserId;
    }
}
