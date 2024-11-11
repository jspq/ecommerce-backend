package com.challenge.ecommerce.modules.users.domain.repository;

import com.challenge.ecommerce.modules.users.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
