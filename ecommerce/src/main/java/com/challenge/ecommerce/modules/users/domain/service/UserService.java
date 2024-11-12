package com.challenge.ecommerce.modules.users.domain.service;

import com.challenge.ecommerce.modules.users.adapters.input.dto.user.RegisterRequestDTO;
import com.challenge.ecommerce.modules.users.adapters.input.dto.user.RegisterResponseDTO;
import com.challenge.ecommerce.modules.users.adapters.input.dto.user.UserRequestDTO;
import com.challenge.ecommerce.modules.users.adapters.input.dto.user.UserResponseDTO;
import com.challenge.ecommerce.modules.users.domain.model.Role;
import com.challenge.ecommerce.modules.users.domain.model.User;
import com.challenge.ecommerce.modules.users.domain.repository.RoleRepository;
import com.challenge.ecommerce.modules.users.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        //TODO: Hacer validacion si el usuario ya existe en base de datos para evitar duplicados
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setEmail(userRequestDTO.getEmail());
        Set<Role> roles = userRequestDTO.getRoles().stream()
                .map(roleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO) {
        User user = new User();
        //TODO: Hacer validacion si el usuario ya existe en base de datos para evitar duplicados
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setNames(registerRequestDTO.getNames());
        user.setSurnames(registerRequestDTO.getSurnames());
        user.setAdress(registerRequestDTO.getAdress());
        user.setEmail(registerRequestDTO.getEmail());

        Set<Role> roleUser = new HashSet<>();
        roleUser.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roleUser);

        User savedUser = userRepository.save(user);
        return matToRegisterUserResponse(savedUser);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userToUpdate.setUsername(userRequestDTO.getUsername());
        userToUpdate.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userToUpdate.setEmail(userRequestDTO.getEmail());

        Set<Role> roles = userRequestDTO.getRoles().stream()
                .map(roleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        userToUpdate.setRoles(roles);

        User updatedUser = userRepository.save(userToUpdate);
        return mapToUserResponse(updatedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public UserResponseDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToUserResponse(user);
    }
    //TODO: Implementar buscar por nombre

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO mapToUserResponse(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return userResponseDTO;
    }

    private RegisterResponseDTO matToRegisterUserResponse(User user) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        registerResponseDTO.setUsername(user.getUsername());
        registerResponseDTO.setNames(user.getNames());
        registerResponseDTO.setSurnames(user.getSurnames());
        registerResponseDTO.setAdress(user.getAdress());
        registerResponseDTO.setEmail(user.getEmail());
        registerResponseDTO.setNumberOfOrders(user.getNumberOfOrders());

        return registerResponseDTO;
    }
}
