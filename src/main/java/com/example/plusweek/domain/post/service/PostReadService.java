package com.example.plusweek.domain.post.service;

import com.example.plusweek.domain.post.dto.PostGetAllRes;
import com.example.plusweek.domain.post.dto.PostGetRes;
import com.example.plusweek.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PostReadService {
    Page<PostGetAllRes> getAllPosts(int page, int size, String sortBy, boolean isAsc);
    PostGetRes getPost(Long postId);
    Post getPostEntity(Long postId);
}
