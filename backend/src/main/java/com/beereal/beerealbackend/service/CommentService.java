package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment addComment(Comment comment);
    List<String> getCommentTextsByBarId(int barId);
}