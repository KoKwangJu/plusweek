package com.example.plusweek.domain.post.service;

import com.example.plusweek.domain.post.dto.PostCreateReq;
import com.example.plusweek.domain.post.dto.PostCreateRes;
import com.example.plusweek.domain.post.dto.PostUpdateReq;
import com.example.plusweek.domain.post.dto.PostUpdateRes;
import com.example.plusweek.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    PostCreateRes createPost(PostCreateReq req, User user);
    PostUpdateRes updatePost(Long postId, PostUpdateReq req, User user);
    void deletePost(Long postId, User user);
}
