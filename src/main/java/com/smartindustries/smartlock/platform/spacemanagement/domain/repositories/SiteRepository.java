package com.smartindustries.smartlock.platform.spacemanagement.domain.repositories;

import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Site;

import java.util.Optional;

public interface SiteRepository {
    Optional<Site> findById(Long id);
    Site save(Site site);
}
