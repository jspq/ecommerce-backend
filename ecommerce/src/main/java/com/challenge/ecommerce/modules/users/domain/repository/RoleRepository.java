package com.challenge.ecommerce.modules.users.domain.repository;

import com.challenge.ecommerce.modules.users.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
