package com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.domain.repositories.UserRepository;
import com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.assemblers.UserPersistenceAssembler;
import com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.repositories.UserPersistenceRepository;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserPersistenceRepository userPersistenceRepository;

    public UserRepositoryImpl(UserPersistenceRepository userPersistenceRepository){
        this.userPersistenceRepository = userPersistenceRepository;
    }

    @Override
    public User save(User user) {
        var saved = userPersistenceRepository.save(UserPersistenceAssembler.toPersistenceFromDomain(user));
        return UserPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userPersistenceRepository.existsByEmail(new Email(email));
    }
}
