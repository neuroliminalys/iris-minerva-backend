package fr.iris_minerva.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.iris_minerva.infrastructure.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    // This is the injected user repository
    private final UserRepository userRepository;

    // Inject user repository
    public AuthService(UserRepository userRepositoryInjected) {
        this.userRepository = userRepositoryInjected;
    }

    @Override
    public UserDetails loadUserByUsername(String userLoginInfos) throws UsernameNotFoundException {

        return this.userRepository.findByUsername(userLoginInfos)
                .or(() -> this.userRepository.findByEmail(userLoginInfos))
                .orElseThrow(() -> new UsernameNotFoundException("Wrong login infos"));
    }
}