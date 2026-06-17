package com.smartindustries.smartlock.platform.administration.application.internal.queryservices;

import com.smartindustries.smartlock.platform.administration.application.queryservices.MembershipQueryService;
import com.smartindustries.smartlock.platform.administration.domain.model.queries.GetUsersByOrganizationIdQuery;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.MembershipPersistenceEntity;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.repositories.MembershipPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipQueryServiceImpl implements MembershipQueryService {

    private final MembershipPersistenceRepository persistenceRepository;

    public MembershipQueryServiceImpl(MembershipPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public List<MembershipPersistenceEntity> handle(GetUsersByOrganizationIdQuery query) {
        return persistenceRepository.findByRole_Organization_Id(query.organizationId());
    }
}
