package com.example.hw27security.Controller;

import com.example.hw27security.Api.ApiResponse;
import com.example.hw27security.Model.Blog;
import com.example.hw27security.Model.User;
import com.example.hw27security.Service.AuthService;
import com.example.hw27security.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {


    private final BlogService blogService;
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/getuserblogs")
    public ResponseEntity getAllUserBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getAllUserBlogs(user.getId(), user.getId()));
    }

    @GetMapping("/getblogby/{id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),id));
    }
    @GetMapping("/getblogby/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(),title));
    }

    @PostMapping("/addblog")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("/updateblog/{id}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal User user,@PathVariable Integer id, @RequestBody @Valid Blog blog) {

        blogService.updateBlog(user.getId(),id, blog);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));
    }

    @DeleteMapping("/deleteblog/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer id){

        blogService.deleteBlog(user.getId(),id);
        return ResponseEntity.status(200).body(new ApiResponse("deleted"));



    }
}
