package com.example.hw27security.Service;

import com.example.hw27security.Api.ApiException;
import com.example.hw27security.Model.Blog;
import com.example.hw27security.Model.User;
import com.example.hw27security.Repository.AuthRepository;
import com.example.hw27security.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }
    public List<Blog> getAllUserBlogs(Integer user_id, Integer id){
        User user = authRepository.findUsersById(user_id);
       return blogRepository.findAllByUser(user);
    }
    public Blog getBlogById(Integer user_id,Integer blog_id){
        User user = authRepository.findUsersById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);
        if (blog == null) {

            throw new ApiException("not found");
        }

        if(blog.getUser().getId() != user_id){
            throw new ApiException("not authorisation");
        }
        return blog;
    }

    public Blog getBlogByTitle(Integer user_id,String title){
        User user = authRepository.findUsersById(user_id);
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null) {

            throw new ApiException("not found");
        }

        if(blog.getUser().getId() != user_id){
            throw new ApiException("not authorisation");
        }
        return blog;
    }

    public void addBlog(Integer user_id,Blog blog){

        User user = authRepository.findUsersById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);

    }

    public void updateBlog(Integer user_id,Integer blog_id,Blog blog){
        User user = authRepository.findUsersById(user_id);
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {

            throw new ApiException("not found");
        }
        if(blog1.getUser().getId() != user_id){
            throw new ApiException("not authorisation");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);

    }

    public void deleteBlog(Integer user_id,Integer blog_id){

        User user = authRepository.findUsersById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);
        if (blog == null) {

            throw new ApiException("not found");
        }

        if(blog.getUser().getId() != user_id){
            throw new ApiException("not authorisation");
        }

        blogRepository.delete(blog);

    }
}
