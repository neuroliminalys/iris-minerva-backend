package fr.iris_minerva.presentation.routes;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iris_minerva.application.service.TokenService;
import fr.iris_minerva.core.entity.system.User;
import fr.iris_minerva.infrastructure.repository.RoleRepository;
import fr.iris_minerva.infrastructure.repository.UserRepository;

@RestController
public class AuthController {
    // remplacer les injections de dépendance et le constructeur
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(
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

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // si contient @ alors = email
        Authentication auth = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()));
        String token = tokenService.generateToken(auth);
        return token;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        System.out.println(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
