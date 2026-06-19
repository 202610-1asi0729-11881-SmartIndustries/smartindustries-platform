package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Site;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.SiteRepository;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.assemblers.SitePersistenceAssembler;
import com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.repositories.SitePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SiteRepositoryImpl implements SiteRepository {

    private final SitePersistenceRepository jpaRepository;

    public SiteRepositoryImpl(SitePersistenceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Site> findById(Long id) {
        return jpaRepository.findById(id)
                .map(SitePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Site save(Site site) {
        var entity = SitePersistenceAssembler.toPersistenceFromDomain(site);
        var saved = jpaRepository.save(entity);
        return SitePersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
