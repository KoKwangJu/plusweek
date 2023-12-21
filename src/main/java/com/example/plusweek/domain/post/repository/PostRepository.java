package com.example.plusweek.domain.post.repository;

import com.example.plusweek.domain.post.entity.Post;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Post.class, idClass = Long.class)
public interface PostRepository {

    Post save(Post post);
    Page<Post> findAll(Pageable pageble);
    Post findByPostId(Long postId);
    void delete(Post post);
    public void deleteAllByModifiedAtLessThanEqual(LocalDateTime standaed);
}
