package com.smartindustries.smartlock.platform.administration.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RootRoleCreatedEvent extends ApplicationEvent {

    private final Long roleId;
    private final Long organizationId;
    private final Long creatorUserId;

    public RootRoleCreatedEvent(Object source, Long roleId, Long organizationId, Long creatorUserId) {
        super(source);
        this.roleId = roleId;
        this.organizationId = organizationId;
        this.creatorUserId = creatorUserId;
    }
}
