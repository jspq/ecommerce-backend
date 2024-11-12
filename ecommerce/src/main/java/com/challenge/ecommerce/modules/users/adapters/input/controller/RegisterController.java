package com.challenge.ecommerce.modules.users.adapters.input.controller;

import com.challenge.ecommerce.modules.users.adapters.input.dto.user.RegisterRequestDTO;
import com.challenge.ecommerce.modules.users.adapters.input.dto.user.RegisterResponseDTO;
import com.challenge.ecommerce.modules.users.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<RegisterResponseDTO> registerUser(
            @RequestBody RegisterRequestDTO registerRequestDTO) {
        RegisterResponseDTO registerResponseDTO = userService.registerUser(registerRequestDTO);
        return ResponseEntity.ok(registerResponseDTO);
    }

}
