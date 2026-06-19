package com.smartindustries.smartlock.platform.iam.application.internal.commandservices;

import com.smartindustries.smartlock.platform.iam.application.commandservices.UserCommandService;
import com.smartindustries.smartlock.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.smartindustries.smartlock.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.smartindustries.smartlock.platform.iam.domain.model.aggregates.User;
import com.smartindustries.smartlock.platform.iam.domain.model.commands.SignInCommand;
import com.smartindustries.smartlock.platform.iam.domain.model.commands.SignUpCommand;
import com.smartindustries.smartlock.platform.iam.domain.repositories.UserRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Password;
import org.apache.commons.lang3.tuple.ImmutablePair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
    public Result<ImmutablePair<User, String>, ApplicationError> handle(SignInCommand command) {
        try {
            var email = new Email(command.email());
            var user = userRepository.findUserByEmail(email);
            if (user.isEmpty()) {
                return Result.failure(ApplicationError.notFound("User", command.email()));
            }
            if (!hashingService.matches(command.password(), user.get().getPassword())) {
                return Result.failure(ApplicationError.validationError("credentials", "Invalid email or password"));
            }

            var token = tokenService.generateToken(user.get().getEmail().address());
            return Result.success(ImmutablePair.of(user.get(), token));
        } catch (IllegalArgumentException e) {
            log.error("Validation error during sign-in", e);
            return Result.failure(ApplicationError.validationError("User", e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error during sign-in", e);
            return Result.failure(ApplicationError.unexpected("sign-in", e.getMessage()));
        }
    }

    @Override
    public Result<User, ApplicationError> handle(SignUpCommand command) {
        try {
            var email = new Email(command.email());
            var password = new Password(command.password());

            if (userRepository.existsByEmail(email)) {
                return Result.failure(ApplicationError.conflict("User", "Email already exists"));
            }

            var user = new User(command.firstName(), command.lastName(), hashingService.encode(password.value()), command.email());
            userRepository.save(user);
            return userRepository.findUserByEmail(email)
                    .<Result<User, ApplicationError>>map(Result::success)
                    .orElseGet(() -> Result.failure(ApplicationError.unexpected("sign-up", "Created user could not be reloaded")));
        } catch (IllegalArgumentException e) {
            log.error("Validation error during sign-up", e);
            return Result.failure(ApplicationError.validationError("User", e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error during sign-up", e);
            return Result.failure(ApplicationError.unexpected("sign-up", e.getMessage()));
        }
    }
}
