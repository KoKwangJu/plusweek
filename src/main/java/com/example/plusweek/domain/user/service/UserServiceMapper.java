package com.example.plusweek.domain.user.service;

import com.example.plusweek.domain.user.dto.UserLoginRes;
import com.example.plusweek.domain.user.dto.UserSignupRes;
import com.example.plusweek.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserServiceMapper {
    UserServiceMapper INSTANCE = Mappers.getMapper(UserServiceMapper.class);

    UserSignupRes toUserSignupRes(User user);

    UserLoginRes toUserLoginRes(User user);
}

