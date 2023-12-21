package com.example.plusweek.domain.comment.repository;

import com.example.plusweek.domain.comment.entity.Comment;
import com.example.plusweek.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository {
    Comment save(Comment comment);

    Comment findByCommentId(Long commentId);

    void delete(Comment comment);

    Page<Comment> findAllByPost(Post post, Pageable pageable);
}
