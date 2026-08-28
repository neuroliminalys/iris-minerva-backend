package fr.iris_minerva.presentation.routes;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iris_minerva.application.dto.auth.LoginDto;
import fr.iris_minerva.core.entity.system.User;
import fr.iris_minerva.domain.usecases.AuthUsecases;

@RestController
public class AuthController {
    // remplacer les injections de dépendance et le constructeur
    private final AuthUsecases authUsecases;

    public AuthController(AuthUsecases authUsecasesInjected) {
        this.authUsecases = authUsecasesInjected;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginInfos) {
        // si contient @ alors = email
        return authUsecases.authWithInfos(loginInfos);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return authUsecases.register(user);
    }
}
