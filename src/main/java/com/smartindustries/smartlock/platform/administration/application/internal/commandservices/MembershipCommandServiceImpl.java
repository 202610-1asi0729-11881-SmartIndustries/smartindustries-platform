package com.smartindustries.smartlock.platform.administration.application.internal.commandservices;

import com.smartindustries.smartlock.platform.administration.application.commandservices.MembershipCommandService;
import com.smartindustries.smartlock.platform.administration.domain.model.aggregates.Membership;
import com.smartindustries.smartlock.platform.administration.domain.model.commands.AddRootUserToOrganizationCommand;
import com.smartindustries.smartlock.platform.administration.domain.repositories.MembershipRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {

    private final MembershipRepository membershipRepository;

    public MembershipCommandServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
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
}
