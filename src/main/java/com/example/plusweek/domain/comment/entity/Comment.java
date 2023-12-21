package com.example.plusweek.domain.comment.entity;

import com.example.plusweek.domain.post.entity.Post;
import com.example.plusweek.domain.user.entity.User;
import com.example.plusweek.global.timestamp.TimeStamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String text;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @Builder
    public Comment(Long commentId, String text, User user, Post post) {
        this.commentId = commentId;
        this.text = text;
        this.user = user;
        setPost(post);
    }

    public void setPost(Post post) {
        this.post = post;
    }}
