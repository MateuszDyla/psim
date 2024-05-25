package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Comment;
import com.beereal.beerealbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentByBarId(int barId) {
        Optional<Comment> commentOptional = commentRepository.findFirstByBarId(barId);
        return commentOptional.orElse(null);
    }

    @Override
    public List<String> getCommentTextsByBarId(int barId) {
        List<String> commentsStrings = new ArrayList<String>();
        List<Comment> comments =  commentRepository.findByBarId(barId);
        for (int i = 0; i < comments.size(); i++) {
            commentsStrings.add(comments.get(i).getText());
        }
        return commentsStrings;
    }
}