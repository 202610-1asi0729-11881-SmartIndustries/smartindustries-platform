package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.transform;

import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.OrganizationPersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources.OrganizationSummaryResource;

public final class OrganizationSummaryResourceFromPersistenceAssembler {

    private OrganizationSummaryResourceFromPersistenceAssembler() {
    }

    public static OrganizationSummaryResource toResourceFromPersistence(OrganizationPersistenceEntity entity) {
        var ownerName = entity.getRoles().stream()
                .filter(r -> "Root".equals(r.getName().value()))
                .findFirst()
                .flatMap(r -> r.getMemberships().stream().findFirst())
                .map(m -> m.getUser().getName().getFullName())
                .orElse("Unknown");

        return new OrganizationSummaryResource(
                entity.getId(),
                entity.getName().value(),
                entity.getDescription(),
                ownerName);
    }
}
