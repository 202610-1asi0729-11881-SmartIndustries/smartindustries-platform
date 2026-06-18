package com.smartindustries.smartlock.platform.administration.application.internal.commandservices;

import com.smartindustries.smartlock.platform.administration.application.commandservices.MembershipCommandService;
import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Membership;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRootUserToOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.UpdateUserRoleInOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.repositories.MembershipRepository;
import com.smartindustries.smartlock.platform.administration.domain.repositories.RoleRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {

    private final MembershipRepository membershipRepository;
    private final RoleRepository roleRepository;

    public MembershipCommandServiceImpl(MembershipRepository membershipRepository, RoleRepository roleRepository) {
        this.membershipRepository = membershipRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Result<Membership, ApplicationError> handle(AddRootUserToOrganizationCommand command) {
        try {
            var membership = new Membership(command);
            var savedMembership = membershipRepository.save(membership);
            return Result.success(savedMembership);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Membership", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("membership-creation", e.getMessage()));
        }
    }

    @Override
    public Result<Membership, ApplicationError> handle(UpdateUserRoleInOrganizationCommand command) {
        try {
            var membership = membershipRepository.findByUserIdAndOrganizationId(command.userId(), command.organizationId());
            if (membership.isEmpty()) {
                return Result.failure(ApplicationError.notFound("Membership", "User is not a member of this organization"));
            }

            var role = roleRepository.findById(command.newRoleId());
            if (role.isEmpty() || !role.get().getOrganizationId().equals(command.organizationId())) {
                return Result.failure(ApplicationError.validationError("Role", "Role not found or does not belong to this organization"));
            }

            var currentRole = roleRepository.findById(membership.get().getRoleId());
            if (currentRole.isPresent() && "Root".equals(currentRole.get().getName().value())) {
                return Result.failure(ApplicationError.businessRuleViolation("Membership", "Cannot change the role of a root administrator"));
            }

            membership.get().updateRole(command.newRoleId());
            var saved = membershipRepository.save(membership.get());
            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("Membership", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("membership-update", e.getMessage()));
        }
    }
}
