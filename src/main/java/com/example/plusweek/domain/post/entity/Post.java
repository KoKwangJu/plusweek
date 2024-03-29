package com.example.plusweek.domain.post.entity;

import com.example.plusweek.domain.comment.entity.Comment;
import com.example.plusweek.domain.user.entity.User;
import com.example.plusweek.global.timestamp.TimeStamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, length = 5000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(Long postId, String title, String content, User user) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void addComments(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }
}
