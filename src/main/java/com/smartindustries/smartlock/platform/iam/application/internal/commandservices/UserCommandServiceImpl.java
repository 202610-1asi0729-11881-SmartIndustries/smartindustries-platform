package com.smartindustries.smartlock.platform.iam.application.internal.commandservices;

import com.smartindustries.smartlock.platform.iam.application.commandservices.UserCommandService;
import com.smartindustries.smartlock.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.smartindustries.smartlock.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.domain.model.commands.SignUpCommand;
import com.smartindustries.smartlock.platform.iam.domain.repositories.UserRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService){
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    public Result<User, ApplicationError> handle(SignUpCommand command) {
        try {
            var email = new Email(command.email());

            if (userRepository.existsByEmail(email)) {
                return Result.failure(ApplicationError.conflict("User", "Email already exists"));
            }

            var user = new User(command.firstName(), command.lastName(), hashingService.encode(command.password()), command.email());
            userRepository.save(user);
            return userRepository.findUserByEmail(email)
                    .<Result<User, ApplicationError>>map(Result::success)
                    .orElseGet(() -> Result.failure(ApplicationError.unexpected("sign-up", "Created user could not be reloaded")));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("User", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("sign-up", e.getMessage()));
        }
    }
}
