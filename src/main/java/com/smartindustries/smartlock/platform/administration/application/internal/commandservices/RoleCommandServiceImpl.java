package com.smartindustries.smartlock.platform.administration.application.internal.commandservices;

import com.smartindustries.smartlock.platform.administration.application.commandservices.RoleCommandService;
import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateBasicRoleCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateRootRoleCommand;
import com.smartindustries.smartlock.platform.administration.domain.repositories.RoleRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Result<Role, ApplicationError> handle(CreateRootRoleCommand command) {
        try {
            var role = Role.createRoot(command.organizationId());
            roleRepository.save(role, command.creatorUserId());
            return Result.success(role);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Role", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("role-creation", e.getMessage()));
        }
    }

    @Override
    public Result<Role, ApplicationError> handle(CreateBasicRoleCommand command) {
        try {
            var role = Role.createBasic(command.organizationId());
            roleRepository.save(role, null);
            return Result.success(role);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Role", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("role-creation", e.getMessage()));
        }
    }
}
