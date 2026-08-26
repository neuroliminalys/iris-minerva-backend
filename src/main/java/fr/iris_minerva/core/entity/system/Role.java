package fr.iris_minerva.core.entity.system;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    @Column(nullable = false)
    private String authorityName;

    @Override
    public @Nullable String getAuthority() {
        return this.id;
    }

    public void setAuthority(String authorityId) {
        this.id = authorityId;
    }
    
}
