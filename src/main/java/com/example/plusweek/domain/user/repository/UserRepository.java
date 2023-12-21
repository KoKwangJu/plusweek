package com.example.plusweek.domain.user.repository;

import com.example.plusweek.domain.user.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {
    User findByUsername(String username);
    User save(User user);
}
