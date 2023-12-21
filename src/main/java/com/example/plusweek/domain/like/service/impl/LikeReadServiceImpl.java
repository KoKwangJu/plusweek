package com.example.plusweek.domain.like.service.impl;

import com.example.plusweek.domain.like.dto.LikeGetRes;
import com.example.plusweek.domain.like.Repository.LikeRepository;
import com.example.plusweek.domain.like.entity.Like;
import com.example.plusweek.domain.like.service.LikeReadService;
import com.example.plusweek.domain.post.entity.Post;
import com.example.plusweek.domain.post.service.PostReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeReadServiceImpl {
    private final LikeRepository likeRepository;

    private final PostReadService postReadService;

    public LikeGetRes getLike(Long postId) {
        Post post = postReadService.getPostEntity(postId);

        List<Like> likeList = likeRepository.findAllByPost(post);

        return new LikeGetRes(postId, likeList.size());
    }
}
