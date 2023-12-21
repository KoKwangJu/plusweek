package com.example.plusweek.domain.comment.service;

import com.example.plusweek.domain.comment.dto.CommentCreateRes;
import com.example.plusweek.domain.comment.dto.CommentGetRes;
import com.example.plusweek.domain.comment.dto.CommentUpdateRes;
import com.example.plusweek.domain.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentServiceMapper {

    CommentServiceMapper INSTANCE = Mappers.getMapper(CommentServiceMapper.class);

    CommentCreateRes toCommentCreateRes(Comment comment);

    CommentUpdateRes toCommentUpdateRes(Comment comment);

    CommentGetRes toCommentGetRes(Comment comment);
}
