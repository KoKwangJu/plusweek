package com.example.plusweek.domain.like.service;

import com.example.plusweek.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    void clickLike(Long postId, User user);
}
