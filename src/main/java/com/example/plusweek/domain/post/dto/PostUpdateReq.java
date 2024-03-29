package com.example.plusweek.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostUpdateReq {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
