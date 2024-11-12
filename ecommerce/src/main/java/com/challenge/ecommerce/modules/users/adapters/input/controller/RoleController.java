package com.challenge.ecommerce.modules.users.adapters.input.controller;

import com.challenge.ecommerce.modules.users.adapters.input.dto.role.RoleRequestDTO;
import com.challenge.ecommerce.modules.users.adapters.input.dto.role.RoleResponseDTO;
import com.challenge.ecommerce.modules.users.domain.model.Role;
import com.challenge.ecommerce.modules.users.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        RoleResponseDTO roleResponseDTO = roleService.createRole(roleRequestDTO);
        return ResponseEntity.ok(roleResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> findRoleById(@PathVariable Long id) {
        RoleResponseDTO roleResponseDTO = roleService.findRoleById(id);
        return ResponseEntity.ok(roleResponseDTO);
    }
}
