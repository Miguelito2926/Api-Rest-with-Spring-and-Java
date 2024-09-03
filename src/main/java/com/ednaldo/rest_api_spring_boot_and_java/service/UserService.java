package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private Logger logger = Logger.getLogger(UserService.class.getName());

    private final UserRepository userRepository;

       @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Encontrando um usuário onr pelo nome: " + username);
        var user = userRepository.findbyUserName(username);
        if (user != null){
        return user;
        }else {
            throw new UsernameNotFoundException("Ususário não existe." + username);
        }
    }
}
