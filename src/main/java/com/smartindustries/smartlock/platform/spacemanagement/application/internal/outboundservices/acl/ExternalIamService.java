package com.smartindustries.smartlock.platform.spacemanagement.application.internal.outboundservices.acl;

import com.smartindustries.smartlock.platform.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalIamService {

    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<Long> fetchUserIdByEmail(String email) {
        var userId = iamContextFacade.fetchUserIdByEmail(email);
        return userId == 0L ? Optional.empty() : Optional.of(userId);
    }
}
