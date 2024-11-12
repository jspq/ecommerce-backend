package com.challenge.ecommerce.modules.users.infraestructure.security;

import com.challenge.ecommerce.modules.users.domain.model.User;
import com.challenge.ecommerce.modules.users.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario."));
            return new UserPrincipal(user);
        } catch (Exception e) {
            logger.error("Error en loadUserByUsername: ", e);
            throw new InternalAuthenticationServiceException("Error de autenticación", e);
        }
    }
}
