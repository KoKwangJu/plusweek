package com.example.plusweek.domain.comment.service;

import com.example.plusweek.domain.comment.dto.CommentGetRes;
import com.example.plusweek.domain.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CommentReadService {
    Comment getCommentEntity(Long commentId);

    public Page<CommentGetRes> getAllComments(Long postId, int page, int size, String sortBy,
        boolean isAsc);
}
