package com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;

public class UserPersistenceAssembler {

    private UserPersistenceAssembler() {

    }

    public static User toDomainFromPersistence(UserPersistenceEntity entity){
        if (entity == null) return null;
        var domain = new User();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEmail(entity.getEmail());
        domain.setPassword(entity.getPassword());
        return domain;
    }

    public static UserPersistenceEntity toPersistenceFromDomain(User user){
        if (user == null) return null;
        var entity = new UserPersistenceEntity();
        // Only set ID if the user is being updated (has a non-null ID)
        // For new users, leave ID null to allow JPA to generate it
        if (user.getId() != null) {
            entity.setId(user.getId());
        }
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }
}
