package fr.iris_minerva.application.dto.auth;

import fr.iris_minerva.core.entity.system.User;

public record UserDto(
        String id,
        String username,
        String email) {
    public UserDto(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}