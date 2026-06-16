package com.smartindustries.smartlock.platform.spacemanagement.application.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetSitesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.SitePersistenceEntity;

import java.util.List;

public interface SiteQueryService {
    List<SitePersistenceEntity> handle(GetSitesByOrganizationIdQuery query);
}
