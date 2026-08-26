package fr.iris_minerva.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iris_minerva.core.entity.system.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    public Optional<Role> findById(String id);
}
