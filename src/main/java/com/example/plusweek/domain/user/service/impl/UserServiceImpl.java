package com.example.plusweek.domain.user.service.impl;

import com.example.plusweek.domain.user.dto.UserConfirmUsernameReq;
import com.example.plusweek.domain.user.dto.UserConfirmUsernameRes;
import com.example.plusweek.domain.user.dto.UserLoginReq;
import com.example.plusweek.domain.user.dto.UserLoginRes;
import com.example.plusweek.domain.user.dto.UserSignupReq;
import com.example.plusweek.domain.user.dto.UserSignupRes;
import com.example.plusweek.domain.user.entity.Role;
import com.example.plusweek.domain.user.entity.User;
import com.example.plusweek.domain.user.repository.UserRepository;
import com.example.plusweek.domain.user.service.UserService;
import com.example.plusweek.domain.user.service.UserServiceMapper;
import com.example.plusweek.domain.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserConfirmUsernameRes confirmUsername(UserConfirmUsernameReq req) {
        boolean duplicated = userRepository.findByUsername(req.getUsername()) != null;
        return UserConfirmUsernameRes.builder().duplicated(duplicated).build();
    }



    @Override
    public UserSignupRes signup(UserSignupReq req) {
        UserValidator.validate(req);
        User user = userRepository.findByUsername(req.getUsername());
        UserValidator.checkIsDuplicatedName(user);


        User saveUser = userRepository.save(User.builder()
            .username(req.getUsername())
            .password(passwordEncoder.encode(req.getPassword()))
            .role(Role.ROLE_USER)
            .build());

        return UserServiceMapper.INSTANCE.toUserSignupRes(saveUser);
    }

    @Override
    public UserLoginRes login(UserLoginReq req) {
        User user = userRepository.findByUsername(req.getUsername());
        UserValidator.validate(user);

        String password = req.getPassword();
        UserValidator.verifyPassword(passwordEncoder, password, user.getPassword());

        return UserServiceMapper.INSTANCE.toUserLoginRes(user);
    }
}
