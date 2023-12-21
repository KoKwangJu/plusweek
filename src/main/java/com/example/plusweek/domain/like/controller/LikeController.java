package com.example.plusweek.domain.like.controller;

import com.example.plusweek.domain.like.dto.LikeGetRes;
import com.example.plusweek.domain.like.service.LikeReadService;
import com.example.plusweek.domain.like.service.LikeService;
import com.example.plusweek.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final LikeReadService likeReadService;

    @GetMapping("/{postId}/likes")
    public ResponseEntity<LikeGetRes> getPostLike(
        @PathVariable(name = "postId") Long postId
    ) {
        LikeGetRes res = likeReadService.getLike(postId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<Void> clickPostLike(
        @PathVariable(name = "postId") Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        likeService.clickLike(postId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
