package com.example.plusweek.domain.post.service.impl;

import com.example.plusweek.domain.post.dto.PostCreateReq;
import com.example.plusweek.domain.post.dto.PostCreateRes;
import com.example.plusweek.domain.post.dto.PostUpdateReq;
import com.example.plusweek.domain.post.dto.PostUpdateRes;
import com.example.plusweek.domain.post.entity.Post;
import com.example.plusweek.domain.post.repository.PostRepository;
import com.example.plusweek.domain.post.service.PostService;
import com.example.plusweek.domain.post.service.PostServiceMapper;
import com.example.plusweek.domain.post.validator.PostValidator;
import com.example.plusweek.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostReadServiceImpl postReadService;

    @Override
    public PostCreateRes createPost(PostCreateReq req, User user) {
        Post savePost = postRepository.save(Post.builder()
            .title(req.getTitle())
            .content(req.getContent())
            .user(user)
            .build()
        );

        return PostServiceMapper.INSTANCE.toPostCreateRes(savePost);
    }

    @Override
    public PostUpdateRes updatePost(Long postId, PostUpdateReq req, User user) {
        Post post = postReadService.getPostEntity(postId);

        PostValidator.checkPostAuthor(post, user);

        postRepository.save(Post.builder()
            .postId(postId)
            .title(req.getTitle())
            .content(req.getContent())
            .user(user)
            .build()
        );

        return PostServiceMapper.INSTANCE.toPostUpdateRes(postReadService.getPostEntity(postId));
    }

    @Override
    public void deletePost(Long postId, User user) {
        Post post = postReadService.getPostEntity(postId);

        PostValidator.checkPostAuthor(post, user);

        postRepository.delete(post);
    }

}
