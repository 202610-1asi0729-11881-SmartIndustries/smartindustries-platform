package com.smartindustries.smartlock.platform.administration.application.internal.commandservices;

import com.smartindustries.smartlock.platform.administration.application.commandservices.RoleCommandService;
import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Role;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRoleToOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateBasicRoleCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.CreateRootRoleCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.UpdateRoleInformationCommand;
import com.smartindustries.smartlock.platform.administration.domain.repositories.RoleRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.GenericName;
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
            var savedRole = roleRepository.save(role, command.creatorUserId());
            return Result.success(savedRole);
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
            var savedRole = roleRepository.save(role, null);
            return Result.success(savedRole);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Role", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("role-creation", e.getMessage()));
        }
    }

    @Override
    public Result<Role, ApplicationError> handle(AddRoleToOrganizationCommand command) {
        try {
            var name = new GenericName(command.name());

            if (roleRepository.existsByOrganizationIdAndName(command.organizationId(), name)) {
                return Result.failure(ApplicationError.conflict("Role", "Name already exists in this organization"));
            }

            var role = Role.create(command);
            var savedRole = roleRepository.save(role, null);
            return Result.success(savedRole);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Role", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("role-creation", e.getMessage()));
        }
    }

    @Override
    public Result<Role, ApplicationError> handle(UpdateRoleInformationCommand command) {
        try {
            var role = roleRepository.findById(command.roleId());
            if (role.isEmpty()) {
                return Result.failure(ApplicationError.notFound("Role", command.roleId().toString()));
            }

            role.get().updateInformation(command);
            var saved = roleRepository.save(role.get(), null);
            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Role", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("role-update", e.getMessage()));
        }
    }
}
