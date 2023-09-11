package com.kenzie.chat.webapi.service;

import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.usersystem.UserService;
import com.kenzie.chat.webapi.repositories.CommentRepository;
import com.kenzie.chat.webapi.repositories.model.CommentRecord;
import com.kenzie.chat.webapi.service.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public Comment findById(String id) {
        Comment commentFromBackend = commentRepository
                .findById(id)
                .map(comment -> new Comment(comment.getId(), comment.getOwner(), comment.getTitle(), comment.getContent()) )
                .orElse(null);
        return commentFromBackend;
    }

    public List<Comment> findByOwner(String username) {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findByOwner(username)
                .forEach(comment -> comments.add(new Comment(comment.getId(), comment.getOwner(), comment.getTitle(), comment.getContent())));
        return comments;
    }

    public List<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();
        commentRepository
                .findAll()
                .forEach(comment -> comments.add(new Comment(comment.getId(), comment.getOwner(), comment.getTitle(), comment.getContent())));
        return comments;
    }

    public Comment addNewComment(Comment comment) {
        UserDto user = userService.getUser(comment.getOwner());
        if (user == null || !user.isActive()) {
            throw new IllegalArgumentException("Cannot post comment: User is not valid");
        }

        CommentRecord commentRecord = new CommentRecord();
        commentRecord.setId(comment.getId());
        commentRecord.setOwner(comment.getOwner());
        commentRecord.setTitle(comment.getTitle());
        commentRecord.setContent(comment.getContent());
        commentRepository.save(commentRecord);
        return comment;
    }
}
