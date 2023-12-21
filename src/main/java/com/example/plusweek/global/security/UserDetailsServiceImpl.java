package com.example.plusweek.global.security;

import com.example.plusweek.domain.user.entity.User;
import com.example.plusweek.domain.user.repository.UserRepository;
import com.example.plusweek.domain.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserValidator.validate(user);
        return new UserDetailsImpl(user);
    }
}
