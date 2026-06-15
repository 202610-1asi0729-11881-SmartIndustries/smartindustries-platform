package com.smartindustries.smartlock.platform.iam.infrastructure.acl;

import com.smartindustries.smartlock.platform.iam.application.queryservices.UserQueryService;
import com.smartindustries.smartlock.platform.iam.domain.model.queries.GetUserByEmailQuery;
import com.smartindustries.smartlock.platform.iam.interfaces.acl.IamContextFacade;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import org.springframework.stereotype.Service;

@Service
public class IamContextFacadeImpl implements IamContextFacade {

    private final UserQueryService userQueryService;

    public IamContextFacadeImpl(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    public Long fetchUserIdByEmail(String email) {
        return userQueryService.handle(new GetUserByEmailQuery(new Email(email)))
                .map(user -> user.getId())
                .orElse(0L);
    }
}
