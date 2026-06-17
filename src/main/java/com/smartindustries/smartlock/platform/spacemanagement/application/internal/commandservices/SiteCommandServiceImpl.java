package com.smartindustries.smartlock.platform.spacemanagement.application.internal.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.SiteCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Site;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.AddSiteToOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateSiteInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.OrganizationRepository;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.SiteRepository;
import org.springframework.stereotype.Service;

@Service
public class SiteCommandServiceImpl implements SiteCommandService {

    private final SiteRepository siteRepository;
    private final OrganizationRepository organizationRepository;

    public SiteCommandServiceImpl(SiteRepository siteRepository, OrganizationRepository organizationRepository) {
        this.siteRepository = siteRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Result<Site, ApplicationError> handle(AddSiteToOrganizationCommand command) {
        try {
            if (organizationRepository.findById(command.organizationId()).isEmpty()) {
                return Result.failure(ApplicationError.notFound("Organization", command.organizationId().toString()));
            }

            var site = new Site(command);
            var savedSite = siteRepository.save(site);
            return Result.success(savedSite);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Site", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("site-creation", e.getMessage()));
        }
    }

    @Override
    public Result<Site, ApplicationError> handle(UpdateSiteInformationCommand command) {
        try {
            var site = siteRepository.findById(command.siteId());
            if (site.isEmpty()) {
                return Result.failure(ApplicationError.notFound("Site", command.siteId().toString()));
            }

            site.get().updateInformation(command);
            var saved = siteRepository.save(site.get());
            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Site", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("site-update", e.getMessage()));
        }
    }
}
