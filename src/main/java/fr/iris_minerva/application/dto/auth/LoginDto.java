package fr.iris_minerva.application.dto.auth;

import fr.iris_minerva.core.entity.system.User;

public record LoginDto(
    String login,
    String password
) {
    public LoginDto(User user) {
        this(user.getEmail(), user.getPassword());
    }
}
