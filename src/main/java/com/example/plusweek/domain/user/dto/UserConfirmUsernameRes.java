package com.example.plusweek.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@Getter
public class UserConfirmUsernameRes {
    private boolean duplicated;

    @Builder
    public UserConfirmUsernameRes(boolean duplicated){
        this.duplicated = duplicated;
    }
}
