package com.smartindustries.smartlock.platform.iam.infrastructure.authorization.sfs.services;

import com.smartindustries.smartlock.platform.iam.domain.repositories.UserRepository;
import com.smartindustries.smartlock.platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(new Email(email))
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return UserDetailsImpl.build(user);
    }
}
