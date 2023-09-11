package com.kenzie.chat.usersystem;

import com.kenzie.chat.usersystem.dao.UserDao;
import com.kenzie.chat.usersystem.model.User;
import com.kenzie.chat.usersystem.utilities.DynamoDbClientProvider;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class UserService {

    DynamoDBMapper mapper;
    UserDao userDao;

    public UserService() {
        mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        userDao = new UserDao(mapper);
    }

    public UserService(DynamoDBMapper mapper) {
        this.mapper = mapper;
        userDao = new UserDao(mapper);
    }

    public UserDto getUser(String username) {
        return toUserDto(userDao.getUser(username));
    }

    public UserDto registerUser(String username, String email, String name) {
        User user = new User(username, email, name, convertToString(new Date()), true);
        try {
            userDao.createUser(user);
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("User already exists");
        }

        return toUserDto(user);
    }

    public UserDto deactivateUser(String username) {
        return toUserDto(userDao.deactivateUser(username));
    }

    public List<UserDto> getUsersRegisteredSince(Date fromDate) {
        List<User> users = userDao.getUsersSignedUpInSinceDate(convertToString(fromDate));
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users) {
            userDtos.add(toUserDto(user));
        }
        return userDtos;
    }

    private UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getUsername(), user.getEmail(), user.getName(), user.isActive());
    }

    private String convertToString(Date date) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(date);
    }

    private Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.parse(date);
    }
}
