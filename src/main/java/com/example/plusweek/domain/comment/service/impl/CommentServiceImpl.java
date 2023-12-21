package com.example.plusweek.domain.comment.service.impl;

import com.example.plusweek.domain.comment.dto.CommentCreateReq;
import com.example.plusweek.domain.comment.dto.CommentCreateRes;
import com.example.plusweek.domain.comment.dto.CommentUpdateReq;
import com.example.plusweek.domain.comment.dto.CommentUpdateRes;
import com.example.plusweek.domain.comment.entity.Comment;
import com.example.plusweek.domain.comment.repository.CommentRepository;
import com.example.plusweek.domain.comment.service.CommentReadService;
import com.example.plusweek.domain.comment.service.CommentService;
import com.example.plusweek.domain.comment.service.CommentServiceMapper;
import com.example.plusweek.domain.comment.validator.CommentValidator;
import com.example.plusweek.domain.post.entity.Post;
import com.example.plusweek.domain.post.service.impl.PostReadServiceImpl;
import com.example.plusweek.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    private final CommentReadService commentReadService;

    private final PostReadServiceImpl postReadService;

    @Override
    @Transactional
    public CommentCreateRes createComment(CommentCreateReq req, User user) {
        Post post = postReadService.getPostEntity(req.getPostId());

        Comment saveComment = commentRepository.save(Comment.builder()
            .text(req.getText())
            .user(user)
            .post(post)
            .build()
        );
        post.addComments(saveComment);

        return CommentServiceMapper.INSTANCE.toCommentCreateRes(saveComment);
    }

    @Override
    @Transactional
    public CommentUpdateRes updateComment(Long commentId, CommentUpdateReq req, User user) {
        Post post = postReadService.getPostEntity(req.getPostId());
        Comment comment = commentReadService.getCommentEntity(commentId);

        CommentValidator.checkCommentAuthor(comment, user);

        Comment saveComment = commentRepository.save(Comment.builder()
            .commentId(commentId)
            .text(req.getText())
            .user(user)
            .post(post)
            .build()
        );
        post.addComments(saveComment);

        return CommentServiceMapper.INSTANCE.toCommentUpdateRes(
            commentReadService.getCommentEntity(commentId));
    }

    @Override
    public void deleteComment(Long commentId, User user) {
        Comment comment = commentReadService.getCommentEntity(commentId);

        CommentValidator.checkCommentAuthor(comment, user);

        commentRepository.delete(comment);
    }
}
