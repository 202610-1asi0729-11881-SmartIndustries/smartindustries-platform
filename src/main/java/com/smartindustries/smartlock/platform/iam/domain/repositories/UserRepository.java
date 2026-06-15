package com.smartindustries.smartlock.platform.iam.domain.repositories;

import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByEmail(Email email);

    User save(User user);

    boolean existsByEmail(Email email);
}
