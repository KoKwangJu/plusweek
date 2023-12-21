package com.example.plusweek.domain.user.service;

import com.example.plusweek.domain.user.dto.UserConfirmUsernameReq;
import com.example.plusweek.domain.user.dto.UserConfirmUsernameRes;
import com.example.plusweek.domain.user.dto.UserLoginReq;
import com.example.plusweek.domain.user.dto.UserLoginRes;
import com.example.plusweek.domain.user.dto.UserSignupReq;
import com.example.plusweek.domain.user.dto.UserSignupRes;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserConfirmUsernameRes confirmUsername(UserConfirmUsernameReq req);

    UserSignupRes signup(UserSignupReq req);

    UserLoginRes login(UserLoginReq req);


}
