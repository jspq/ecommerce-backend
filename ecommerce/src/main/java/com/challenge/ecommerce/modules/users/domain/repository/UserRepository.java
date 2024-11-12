package com.challenge.ecommerce.modules.users.domain.repository;

import com.challenge.ecommerce.modules.users.domain.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
}
