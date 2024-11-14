package com.challenge.ecommerce.infraestructure.security;

import com.challenge.ecommerce.modules.users.domain.model.User;
import com.challenge.ecommerce.modules.users.domain.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    UserRepository userRepository;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expirationTime}")
    private long expirationTime;

    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList());
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = loadUserByUsername(getUsernameFromToken(token));
        List<GrantedAuthority> authorities = extractRolesFromToken(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public String getUsernameFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return !claims.getExpiration().before(new Date());
        } catch (SignatureException ex) {
            logger.error("Firma inválida para el token: ", ex);
        } catch (MalformedJwtException ex) {
            logger.error("Token malformado: ", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("Token expirado: ", ex);
        } catch (Exception ex) {
            logger.error("Error al validar el token: ", ex);
        }
        return false;
    }

    private UserDetails loadUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario."));

            return new UserPrincipal(user);
        } catch (Exception e) {
            logger.error("Error en loadUserByUsername: ", e);
            throw new InternalAuthenticationServiceException("Error de autenticación", e);
        }
    }

    public List<GrantedAuthority> extractRolesFromToken(String token) {
        Claims claims = extractClaims(token);
        List<String> roles = claims.get("roles", List.class);
        logger.info("Roles obtenidos del token: " + roles);
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Claims extractClaims(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new RuntimeException("Firma del JWT invalida");
        }
    }
}
