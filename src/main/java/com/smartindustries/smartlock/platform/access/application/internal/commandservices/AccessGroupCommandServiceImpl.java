package com.smartindustries.smartlock.platform.access.application.internal.commandservices;

import com.smartindustries.smartlock.platform.access.application.commandservices.AccessGroupCommandService;
import com.smartindustries.smartlock.platform.access.application.internal.outboundservices.acl.ExternalSpaceManagementService;
import com.smartindustries.smartlock.platform.access.domain.model.aggregates.AccessGroup;
import com.smartindustries.smartlock.platform.access.domain.model.commands.CreateAccessGroupCommand;
import com.smartindustries.smartlock.platform.access.domain.repositories.AccessGroupRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class AccessGroupCommandServiceImpl implements AccessGroupCommandService {

    private final AccessGroupRepository accessGroupRepository;
    private final ExternalSpaceManagementService externalSpaceManagementService;

    public AccessGroupCommandServiceImpl(AccessGroupRepository accessGroupRepository, ExternalSpaceManagementService externalSpaceManagementService) {
        this.accessGroupRepository = accessGroupRepository;
        this.externalSpaceManagementService = externalSpaceManagementService;
    }

    @Override
    public Result<AccessGroup, ApplicationError> handle(CreateAccessGroupCommand command) {
        try {
            if (!externalSpaceManagementService.organizationExists(command.organizationId())) {
                return Result.failure(ApplicationError.notFound("Organization", command.organizationId().toString()));
            }

            var accessGroup = new AccessGroup(command);
            var savedAccessGroup = accessGroupRepository.save(accessGroup);
            return Result.success(savedAccessGroup);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("AccessGroup", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("access-group-creation", e.getMessage()));
        }
    }
}
