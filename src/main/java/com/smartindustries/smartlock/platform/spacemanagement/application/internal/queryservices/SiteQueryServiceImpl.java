package com.smartindustries.smartlock.platform.spacemanagement.application.internal.queryservices;

import com.smartindustries.smartlock.platform.spacemanagement.application.queryservices.SiteQueryService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.queries.GetSitesByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities.SitePersistenceEntity;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.SitePersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteQueryServiceImpl implements SiteQueryService {

    private final SitePersistenceRepository persistenceRepository;

    public SiteQueryServiceImpl(SitePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<SitePersistenceEntity> handle(GetSitesByOrganizationIdQuery query) {
        return persistenceRepository.findByOrganization_Id(query.organizationId());
    }
}
