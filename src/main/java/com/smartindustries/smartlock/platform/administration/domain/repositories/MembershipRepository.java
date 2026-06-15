package com.smartindustries.smartlock.platform.administration.domain.repositories;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Membership;

import java.util.Optional;

public interface MembershipRepository {
    Optional<Membership> findById(Long id);
    Membership save(Membership membership);
}
