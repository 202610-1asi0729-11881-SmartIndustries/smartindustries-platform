package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.assemblers;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Membership;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.MembershipPersistenceEntity;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.RolePersistenceEntity;
import com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;

public final class MembershipPersistenceAssembler {

    private MembershipPersistenceAssembler() {
    }

    public static Membership toDomainFromPersistence(MembershipPersistenceEntity entity) {
        if (entity == null) return null;
        var domain = new Membership();
        domain.setId(entity.getId());
        domain.setUserId(entity.getUser().getId());
        domain.setRoleId(entity.getRole().getId());
        return domain;
    }

    public static MembershipPersistenceEntity toPersistenceFromDomain(Membership domain) {
        if (domain == null) return null;
        var entity = new MembershipPersistenceEntity();
        if (domain.getId() != null) entity.setId(domain.getId());
        if (domain.getUserId() != null) {
            var userRef = new UserPersistenceEntity();
            userRef.setId(domain.getUserId());
            entity.setUser(userRef);
        }
        if (domain.getRoleId() != null) {
            var roleRef = new RolePersistenceEntity();
            roleRef.setId(domain.getRoleId());
            entity.setRole(roleRef);
        }
        return entity;
    }
}
