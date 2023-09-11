package com.kenzie.chat.webapi.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.usersystem.UserService;
import com.kenzie.chat.usersystem.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    private UserService userService;
    private DynamoDBMapper mapper;

    @BeforeEach
    void setup() {
        mapper = mock(DynamoDBMapper.class);

        userService = new UserService(mapper);
    }
    /** ------------------------------------------------------------------------
     *  exampleService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void createUser() {
        // GIVEN
        String username = "my username";
        String name = "my name";
        String email = "my email";
        ArgumentCaptor<User> userRecordCaptor = ArgumentCaptor.forClass(User.class);

        // WHEN
        UserDto userDto = userService.registerUser(username, email, name);

        // THEN
        verify(mapper).save(userRecordCaptor.capture(), any(DynamoDBSaveExpression.class));
        User user = userRecordCaptor.getValue();
        Assertions.assertNotNull(user, "The record is saved");
        Assertions.assertEquals(user.getUsername(), username, "The username matches");
        Assertions.assertEquals(user.getName(), name, "The name matches");
        Assertions.assertEquals(user.getEmail(), email, "The email matches");
        Assertions.assertNotNull(user.getJoinDate(), "The join date is populated");

        Assertions.assertNotNull(userDto, "The object is returned");
        Assertions.assertEquals(userDto.getUsername(), username, "The returned username matches");
        Assertions.assertEquals(userDto.getName(), name, "The returned name matches");
        Assertions.assertEquals(userDto.getEmail(), email, "The returned email matches");
    }

}
