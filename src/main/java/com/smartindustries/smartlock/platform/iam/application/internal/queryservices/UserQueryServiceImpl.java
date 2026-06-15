package com.smartindustries.smartlock.platform.iam.application.internal.queryservices;

import com.smartindustries.smartlock.platform.iam.application.queryservices.UserQueryService;
import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.domain.model.queries.GetUserByEmailQuery;
import com.smartindustries.smartlock.platform.iam.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findUserByEmail(query.email());
    }
}
