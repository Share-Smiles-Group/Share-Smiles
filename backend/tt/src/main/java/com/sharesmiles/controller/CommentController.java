// package com.sharesmiles.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.sharesmiles.model.Comment;
// import com.sharesmiles.service.CommentService;

// @RestController
// @RequestMapping("/api/comment")
// public class CommentController {
//     @Autowired
//     private CommentService commentService;

//     @PostMapping("/create")
//     public ResponseEntity<Comment> commentCreate(@RequestBody Comment comment){
//         Comment createdComment = commentService.commentCreate(comment);
//         return new ResponseEntity<Comment>(createdComment, HttpStatus.CREATED);
//     }

//     @PutMapping("/edit/{mid}")
//     public ResponseEntity<Comment> edit(@PathVariable Long mid, @RequestParam Long uid, @RequestBody String newContent) {
//         Comment editedComment = commentService.commentEdit(uid, mid, newContent);
//         return new ResponseEntity<Comment>(editedComment, HttpStatus.OK);
//     }
// }
