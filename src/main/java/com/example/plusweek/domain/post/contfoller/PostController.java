package com.example.plusweek.domain.post.contfoller;

import com.example.plusweek.domain.post.dto.PostCreateReq;
import com.example.plusweek.domain.post.dto.PostCreateRes;
import com.example.plusweek.domain.post.dto.PostGetAllRes;
import com.example.plusweek.domain.post.dto.PostGetRes;
import com.example.plusweek.domain.post.dto.PostUpdateReq;
import com.example.plusweek.domain.post.dto.PostUpdateRes;
import com.example.plusweek.domain.post.service.PostReadService;
import com.example.plusweek.domain.post.service.PostService;
import com.example.plusweek.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostReadService postReadService;

    @GetMapping()
    public ResponseEntity<Page<PostGetAllRes>> getAllPosts(
        @RequestParam("page") int page,
        @RequestParam("size") int size,
        @RequestParam("sortBy") String sortBy,
        @RequestParam("isAsc") boolean isAsc
    ){
        Page<PostGetAllRes> res = postReadService.getAllPosts(page -1, size, sortBy, isAsc);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/(postsId}")
    public ResponseEntity<PostGetRes> getPost(
        @PathVariable(name = "postId") Long postId
    ) {
        PostGetRes res = postReadService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @PostMapping()
    public ResponseEntity<PostCreateRes> createPost(
        @RequestBody PostCreateReq req,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        PostCreateRes res = postService.createPost(req, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostUpdateRes> updatePost(
        @PathVariable(name = "postId") Long postId,
        @RequestBody PostUpdateReq req,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        PostUpdateRes res = postService.updatePost(postId, req, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
        @PathVariable(name = "postId") Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.deletePost(postId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
