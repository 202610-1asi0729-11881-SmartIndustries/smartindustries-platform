package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.acl;

import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.OrganizationRepository;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.acl.SpaceManagementContextFacade;
import org.springframework.stereotype.Service;

@Service
public class SpaceManagementContextFacadeImpl implements SpaceManagementContextFacade {

    private final OrganizationRepository organizationRepository;

    public SpaceManagementContextFacadeImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public boolean organizationExists(Long organizationId) {
        return organizationRepository.findById(organizationId).isPresent();
    }
}
