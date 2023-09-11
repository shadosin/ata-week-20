package com.kenzie.chat.webapi.controller;

import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.usersystem.UserService;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {

        UserDto user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto;
        try {
            userDto = userService.registerUser(userCreateRequest.getUsername(), userCreateRequest.getEmail(), userCreateRequest.getName());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userDto);
    }
}
