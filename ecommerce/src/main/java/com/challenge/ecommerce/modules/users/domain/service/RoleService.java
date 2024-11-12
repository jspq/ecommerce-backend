package com.challenge.ecommerce.modules.users.domain.service;

import com.challenge.ecommerce.modules.users.adapters.input.dto.role.RoleRequestDTO;
import com.challenge.ecommerce.modules.users.adapters.input.dto.role.RoleResponseDTO;
import com.challenge.ecommerce.modules.users.domain.model.Role;
import com.challenge.ecommerce.modules.users.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setName(roleRequestDTO.getName());

        Role savedRole = roleRepository.save(role);
        return mapToRoleResponse(savedRole);
    }

    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::mapToRoleResponse)
                .toList();
    }

    public RoleResponseDTO findRoleById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        RoleResponseDTO roleResponseDTO = mapToRoleResponse(role);
        return roleResponseDTO;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    private RoleResponseDTO mapToRoleResponse(Role role) {
        RoleResponseDTO roleResponse = new RoleResponseDTO();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        return roleResponse;
    }
}
