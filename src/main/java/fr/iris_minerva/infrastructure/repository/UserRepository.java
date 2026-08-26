package fr.iris_minerva.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iris_minerva.core.entity.system.User;

public interface UserRepository extends JpaRepository<User, String>{
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
