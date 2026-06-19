package com.smartindustries.smartlock.platform.spacemanagement.application.internal.commandservices;

import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
import com.smartindustries.smartlock.platform.spacemanagement.application.commandservices.OrganizationCommandService;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.aggregates.Organization;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.CreateOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.DeleteOrganizationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.model.commands.UpdateOrganizationInformationCommand;
import com.smartindustries.smartlock.platform.spacemanagement.domain.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationCommandServiceImpl implements OrganizationCommandService {

    private final OrganizationRepository organizationRepository;

    public OrganizationCommandServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Result<Organization, ApplicationError> handle(CreateOrganizationCommand command) {
        try {
            var name = new GenericName(command.name());

            if (organizationRepository.existsByName(name)) {
                return Result.failure(ApplicationError.conflict("Organization", "Name already exists"));
            }

            var organization = new Organization(command);
            var savedOrganization = organizationRepository.save(organization, command.creatorUserId());
            return Result.success(savedOrganization);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Organization", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("organization-creation", e.getMessage()));
        }
    }

    @Override
    public Result<Organization, ApplicationError> handle(UpdateOrganizationInformationCommand command) {
        try {
            var organization = organizationRepository.findById(command.organizationId());
            if (organization.isEmpty()) {
                return Result.failure(ApplicationError.notFound("Organization", command.organizationId().toString()));
            }

            organization.get().updateInformation(command);
            var saved = organizationRepository.save(organization.get(), null);
            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Organization", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("organization-update", e.getMessage()));
        }
    }

    @Override
    public Result<Organization, ApplicationError> handle(DeleteOrganizationCommand command) {
        try {
            var organization = organizationRepository.findById(command.organizationId());
            if (organization.isEmpty()) {
                return Result.failure(ApplicationError.notFound("Organization", command.organizationId().toString()));
            }

            organizationRepository.deleteById(command.organizationId());
            return Result.success(organization.get());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("organization-deletion", e.getMessage()));
        }
    }
}
