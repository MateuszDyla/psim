package com.beereal.beerealbackend.controller;

import com.beereal.beerealbackend.dto.CommentDTO;
import com.beereal.beerealbackend.model.Bar;
import com.beereal.beerealbackend.model.Comment;
import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.service.BarService;
import com.beereal.beerealbackend.service.CommentService;
import com.beereal.beerealbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BarService barService;

    @PostMapping("/")
    public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDTO) {
        User user = userService.getUserByID(commentDTO.getUserID());
        if (user == null)
            return ResponseEntity.badRequest().body("User not found");

        Bar bar = barService.getBarByID(commentDTO.getBarID());
        if (bar == null)
            return ResponseEntity.badRequest().body("Bar not found");

        Comment comment = new Comment(user, bar, commentDTO.getDate(), commentDTO.getText());

        Comment savedComment = commentService.addComment(comment);
        if (savedComment == null)
            return ResponseEntity.status(500).body("Failed to add comment");
        return ResponseEntity.ok("Comment added successfully");
    }

    @GetMapping("/bar/{barId}")
    public ResponseEntity<List<String>> getCommentsByBarId(@PathVariable int barId) {
        List<String> comments = commentService.getCommentTextsByBarId(barId);
        return ResponseEntity.ok(comments);
    }
}