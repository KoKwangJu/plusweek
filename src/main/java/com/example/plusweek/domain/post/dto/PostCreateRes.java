package com.example.plusweek.domain.post.dto;

import com.example.plusweek.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRes {
    private Long postId;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    @Builder
    public PostCreateRes(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
    }
}
