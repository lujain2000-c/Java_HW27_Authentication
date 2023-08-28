package com.example.hw27security.Repository;

import com.example.hw27security.Model.Blog;
import com.example.hw27security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer id);
    Blog findBlogByTitle(String title);
    List<Blog> findAllByUser(User user);
}
