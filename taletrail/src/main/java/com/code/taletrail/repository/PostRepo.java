package com.code.taletrail.repository;

import com.code.taletrail.model.Category;
import com.code.taletrail.model.Post;
import com.code.taletrail.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

}
