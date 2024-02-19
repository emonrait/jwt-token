package com.cbl.jwttoken.serviceimpl;


import com.cbl.jwttoken.dto.User;
import com.cbl.jwttoken.exception.ResourceNotFoundException;
import com.cbl.jwttoken.service.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        try {
            User user = userRepository.findUserByUserName(userName, "123456");
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            UserDetails userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(user.getUserName())
                            .password(user.getPassword())
                            .roles(roles.toArray(new String[0]))
                            .build();
            return userDetails;
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }

    }
}