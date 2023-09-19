package com.sharesmiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharesmiles.model.Post;
import com.sharesmiles.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    
    @PostMapping("/create") 
    public ResponseEntity<String> postCreate(@RequestBody Post post) {
        postService.createPost(post); 
        return new ResponseEntity<String>("Create post successfully", HttpStatus.OK);     
    }

    @DeleteMapping("/delete/{postId}") 
    public ResponseEntity<String> deletePost(@PathVariable Long postId, @RequestParam Long userId) {
        postService.deletePost(postId, userId);
        return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
    }
}
