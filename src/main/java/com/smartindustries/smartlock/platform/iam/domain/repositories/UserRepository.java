package com.smartindustries.smartlock.platform.iam.domain.repositories;

import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;

public interface UserRepository {
    User save(User user);

    boolean existsByEmail(String email);
}
