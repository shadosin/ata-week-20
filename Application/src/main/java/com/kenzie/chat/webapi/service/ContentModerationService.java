package com.kenzie.chat.webapi.service;

import com.google.common.annotations.VisibleForTesting;
import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.usersystem.UserService;
import com.kenzie.chat.webapi.service.model.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentModerationService {
    @Value("${max.allowed.comments.for.new.user}")
    private Integer maxAllowedCommentsForNewUser;

    @Value("${new.user.probation.period}")
    private Integer newUserProbationPeriod;

    private CommentService commentService;
    private UserService userService;

    public ContentModerationService(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    public List<UserDto> deactivateSpamAccounts() {
        List<UserDto> users = userService.getUsersRegisteredSince(new Date(System.currentTimeMillis() - newUserProbationPeriod));

        List<UserDto> deactivatedUsers = new ArrayList<>();
        for (UserDto user : users) {
            List<Comment> comments = commentService.findByOwner(user.getUsername());
            if (comments.size() > maxAllowedCommentsForNewUser && user.isActive()) {
                user = userService.deactivateUser(user.getUsername());
                deactivatedUsers.add(user);
            }
        }
        return deactivatedUsers;
    }
}
