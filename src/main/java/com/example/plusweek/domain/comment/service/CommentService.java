package com.example.plusweek.domain.comment.service;

import com.example.plusweek.domain.comment.dto.CommentCreateReq;
import com.example.plusweek.domain.comment.dto.CommentCreateRes;
import com.example.plusweek.domain.comment.dto.CommentUpdateReq;
import com.example.plusweek.domain.comment.dto.CommentUpdateRes;
import com.example.plusweek.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentCreateRes createComment(CommentCreateReq req, User user);

    public CommentUpdateRes updateComment(Long commentId, CommentUpdateReq req, User user);

    public void deleteComment(Long commentId, User user);
}
