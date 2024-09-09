package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.dto.AccountCredentialsDTO;
import com.ednaldo.rest_api_spring_boot_and_java.dto.RegisterRequestDTO;
import com.ednaldo.rest_api_spring_boot_and_java.dto.RegisterResponseDTO;
import com.ednaldo.rest_api_spring_boot_and_java.dto.TokenDTO;
import com.ednaldo.rest_api_spring_boot_and_java.entities.Role;
import com.ednaldo.rest_api_spring_boot_and_java.entities.User;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.RoleRepository;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final RoleRepository roleRepository;

    @Value("${jwt.expires}")
    private Long jwtExpires;

    public TokenDTO login(AccountCredentialsDTO credentialsDTO) {
        User user = userRepository.findByUserName(credentialsDTO.username());
        if (user == null || !user.isLoginCorrect(credentialsDTO, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid");
        }
        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.getId().toString())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(jwtExpires))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenDTO(jwtValue, jwtExpires.toString());
    }

    public RegisterResponseDTO registerUser(RegisterRequestDTO request) throws RoleNotFoundException {
        // Validação de dados
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário não pode ser nulo ou vazio");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }

        // Buscar papéis (roles) no repositório
        Set<Role> roles = roleRepository.findByName(request.getRole().toString());
        if (roles.isEmpty()) {
            throw new RoleNotFoundException("Função não encontrada: " + request.getRole());
        }

        // Criar novo usuário
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRoles(roles);

        // Salvando usuário no banco
        User savedUser = userRepository.save(user);

        return new RegisterResponseDTO(savedUser.getUsername(),savedUser.getRoles());
    }
}
