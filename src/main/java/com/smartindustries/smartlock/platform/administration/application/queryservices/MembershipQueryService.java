package com.smartindustries.smartlock.platform.administration.application.queryservices;

import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetUsersByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.MembershipPersistenceEntity;

import java.util.List;

public interface MembershipQueryService {
    List<MembershipPersistenceEntity> handle(GetUsersByOrganizationIdQuery query);
}
