package com.example.plusweek.domain.like.Repository;

import com.example.plusweek.domain.like.entity.Like;
import com.example.plusweek.domain.post.entity.Post;
import com.example.plusweek.domain.user.entity.User;
import java.util.List;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Like.class, idClass = Long.class)
public interface LikeRepository {
    List<Like> findAllByPost(Post post);

    Like findByPostAndUser(Post post, User user);

    void save(Like like);

    void delete(Like like);
}
