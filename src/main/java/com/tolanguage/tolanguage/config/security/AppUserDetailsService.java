package com.tolanguage.tolanguage.config.security;

import com.tolanguage.tolanguage.model.entity.User;
import com.tolanguage.tolanguage.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public AppUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        return AppUserDetails.toKbaseUserDetails(user);
    }

    public AppUserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        User user = userService.getById(id);
        return AppUserDetails.toKbaseUserDetails(user);
    }
}
