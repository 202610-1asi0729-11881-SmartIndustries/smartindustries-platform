package com.smartindustries.smartlock.platform.spacemanagement.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PersonAddedToOrganizationEvent extends ApplicationEvent {

    private final Long personId;
    private final String fullName;
    private final String identityDocument;

    public PersonAddedToOrganizationEvent(Object source, Long personId, String fullName, String identityDocument) {
        super(source);
        this.personId = personId;
        this.fullName = fullName;
        this.identityDocument = identityDocument;
    }
}
