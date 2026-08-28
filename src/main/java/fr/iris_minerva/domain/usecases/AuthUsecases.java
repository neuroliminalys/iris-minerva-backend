package fr.iris_minerva.domain.usecases;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.iris_minerva.application.dto.auth.LoginDto;
import fr.iris_minerva.application.service.TokenService;
import fr.iris_minerva.core.entity.system.User;
import fr.iris_minerva.infrastructure.repository.RoleRepository;
import fr.iris_minerva.infrastructure.repository.UserRepository;

@Component
public class AuthUsecases {
    // remplacer les injections de dépendance et le constructeur
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthUsecases(
            UserRepository userRepositoryInjected,
            RoleRepository roleRepositoryInjected,
            AuthenticationManager authManagerInjected,
            PasswordEncoder passwordEncoderInjected,
            TokenService tokenServiceInjected) {
        this.userRepository = userRepositoryInjected;
        this.authManager = authManagerInjected;
        this.passwordEncoder = passwordEncoderInjected;
        this.tokenService = tokenServiceInjected;
    }

    public String authWithInfos(LoginDto loginInfos) {
        Authentication auth = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginInfos.login(), loginInfos.password()));
        String token = tokenService.generateToken(auth);
        return token;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
