package com.example.plusweek.domain.post.service;

import com.example.plusweek.domain.post.dto.PostCreateRes;
import com.example.plusweek.domain.post.dto.PostGetAllRes;
import com.example.plusweek.domain.post.dto.PostGetRes;
import com.example.plusweek.domain.post.dto.PostUpdateRes;
import com.example.plusweek.domain.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostServiceMapper {
    PostServiceMapper INSTANCE = Mappers.getMapper(PostServiceMapper.class);
    PostGetAllRes toPostGetAllRes(Post post);
    PostGetRes toPostGetRes(Post post);
    PostCreateRes toPostCreateRes(Post post);
    PostUpdateRes toPostUpdateRes(Post post);
}
