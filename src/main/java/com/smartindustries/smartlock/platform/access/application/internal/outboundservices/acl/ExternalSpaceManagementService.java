package com.smartindustries.smartlock.platform.access.application.internal.outboundservices.acl;

import com.smartindustries.smartlock.platform.spacemanagement.interfaces.acl.SpaceManagementContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalSpaceManagementService {

    private final SpaceManagementContextFacade spaceManagementContextFacade;

    public ExternalSpaceManagementService(SpaceManagementContextFacade spaceManagementContextFacade) {
        this.spaceManagementContextFacade = spaceManagementContextFacade;
    }

    public boolean organizationExists(Long organizationId) {
        return spaceManagementContextFacade.organizationExists(organizationId);
    }
}
