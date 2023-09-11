package com.kenzie.chat.webapi.service;


import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.usersystem.UserService;
import com.kenzie.chat.usersystem.model.User;
import com.kenzie.chat.webapi.repositories.CommentRepository;
import com.kenzie.chat.webapi.repositories.model.CommentRecord;
import com.kenzie.chat.webapi.service.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static java.util.UUID.randomUUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentServiceTest {
    private CommentRepository commentRepository;
    private CommentService commentService;
    private UserService userService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        userService = mock(UserService.class);
        commentService = new CommentService(commentRepository, userService);
    }
    /** ------------------------------------------------------------------------
     *  commentService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void canPostComment() {
        // GIVEN
        String id = "the id";
        String content = "the content";
        String owner = "ownername";
        String title = "the title";
        Comment comment = new Comment(id, owner, title, content);
        ArgumentCaptor<CommentRecord> commentRecordCaptor = ArgumentCaptor.forClass(CommentRecord.class);

        when(commentRepository.save(any(CommentRecord.class))).then(i -> i.getArgumentAt(0, CommentRecord.class));

        UserDto userDto = new UserDto();
        userDto.setUsername(owner);
        userDto.setName("name");
        userDto.setEmail("email");
        userDto.setActive(true);
        when(userService.getUser(owner)).thenReturn(userDto);

        // WHEN
        Comment returnedComment = commentService.addNewComment(comment);

        // THEN
        verify(commentRepository).save(commentRecordCaptor.capture());
        CommentRecord commentRecord = commentRecordCaptor.getValue();

        Assertions.assertNotNull(commentRecord, "The object is saved");
        Assertions.assertEquals(commentRecord.getId(), comment.getId(), "The id matches");
        Assertions.assertEquals(commentRecord.getOwner(), comment.getOwner(), "The owner matches");
        Assertions.assertEquals(commentRecord.getTitle(), comment.getTitle(), "The title matches");
        Assertions.assertEquals(commentRecord.getContent(), comment.getContent(), "The content matches");

        Assertions.assertNotNull(returnedComment, "The object is saved");
        Assertions.assertEquals(returnedComment.getId(), comment.getId(), "The id matches");
        Assertions.assertEquals(returnedComment.getOwner(), comment.getOwner(), "The owner matches");
        Assertions.assertEquals(returnedComment.getTitle(), comment.getTitle(), "The title matches");
        Assertions.assertEquals(returnedComment.getContent(), comment.getContent(), "The content matches");
    }

    @Test
    void findById_invalid() {
        // GIVEN
        String id = randomUUID().toString();

        when(commentRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Comment comment = commentService.findById(id);

        // THEN
        Assertions.assertNull(comment, "The comment is null when not found");
    }

}
