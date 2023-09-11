package com.kenzie.chat.webapi.controller;

import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.usersystem.UserService;
import com.kenzie.chat.webapi.service.ContentModerationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moderation")
public class ContentModerationController {

    private final ContentModerationService contentModerationService;
    private final UserService userService;

    ContentModerationController(ContentModerationService contentModerationService, UserService userService) {
        this.contentModerationService = contentModerationService;
        this.userService = userService;
    }

    @PostMapping("/checkForSpam")
    public ResponseEntity<List<UserDto>> checkForSpam() {
        List<UserDto> deactivatedAccounts = contentModerationService.deactivateSpamAccounts();
        return ResponseEntity.ok().body(deactivatedAccounts);
    }

    @PostMapping("/deactivate/{username}")
    public ResponseEntity<UserDto> deactivateUser(@PathVariable("username") String username) {
        UserDto deactivatedAccount = userService.deactivateUser(username);
        return ResponseEntity.ok().body(deactivatedAccount);
    }
}
