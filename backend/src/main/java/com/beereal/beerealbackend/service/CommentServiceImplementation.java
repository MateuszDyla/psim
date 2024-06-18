package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Comment;
import com.beereal.beerealbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /***
     *
     * @param barId id baru
     * @return lista wszystkich komentarzów (wartości tekstowych) dla podanego baru
     */
    @Override
    public List<String> getCommentTextsByBarId(int barId) {
        List<String> commentsStrings = new ArrayList<>();
        List<Comment> comments =  commentRepository.findByBarId(barId);
        for (Comment comment : comments) {
            commentsStrings.add(comment.getText());
        }
        return commentsStrings;
    }
}