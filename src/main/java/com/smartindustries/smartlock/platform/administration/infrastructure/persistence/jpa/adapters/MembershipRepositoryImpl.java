package com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Membership;
import com.smartindustries.smartlock.platform.administration.domain.repositories.MembershipRepository;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.assemblers.MembershipPersistenceAssembler;
import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.repositories.MembershipPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MembershipRepositoryImpl implements MembershipRepository {

    private final MembershipPersistenceRepository jpaRepository;

    public MembershipRepositoryImpl(MembershipPersistenceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Membership> findById(Long id) {
        return jpaRepository.findById(id)
                .map(MembershipPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Membership save(Membership membership) {
        var entity = MembershipPersistenceAssembler.toPersistenceFromDomain(membership);
        var saved = jpaRepository.save(entity);
        return MembershipPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public Optional<Membership> findByUserIdAndOrganizationId(Long userId, Long organizationId) {
        return jpaRepository.findByUser_IdAndRole_Organization_Id(userId, organizationId)
                .map(MembershipPersistenceAssembler::toDomainFromPersistence);
    }
}
