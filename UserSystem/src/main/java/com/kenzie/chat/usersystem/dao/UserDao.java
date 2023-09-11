package com.kenzie.chat.usersystem.dao;

import com.kenzie.chat.usersystem.model.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public UserDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Creates a new user.
     * @param user The user to create
     * @return the created user
     */
    public User createUser(User user) {
        mapper.save(user, new DynamoDBSaveExpression().withExpected(ImmutableMap.of("username", new ExpectedAttributeValue(false))));
        return user;
    }

    /**
     * Gets an existing user by username.
     * @param username The username of the user
     * @return the user
     */
    public User getUser(String username) {
        return mapper.load(User.class, username);
    }

    /**
     * Deactivates an existing user by username.
     * @param username The username of the user to deactivate
     * @return the updated state of the event
     */
    public User deactivateUser(String username) {
        User userToCancel = mapper.load(User.class, username);
        userToCancel.setActive(false);
        mapper.save(userToCancel);
        return userToCancel;
    }

    /**
     * Retrieves all users between now and the startDate
     * @param startDate The first date to return matches for
     * @return A list of the matches for users joined between the given dates
     */
    public List<User> getUsersSignedUpInSinceDate(String startDate) {

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":start_date", new AttributeValue().withS(startDate));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("joinDate > :start_date")// and :end_date")
                .withExpressionAttributeValues(valueMap);

        return mapper.scan(User.class, scanExpression);
    }

    /**
     * Use the scan() method to retrieve all the deactivated users from the User table.
     * @return the list of deactivated users retrieved from the database
     */
    public List<User> getAllDeactivatedUsers() {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":active", new AttributeValue().withBOOL(false));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("active = :active")
                .withExpressionAttributeValues(valueMap);
        PaginatedScanList<User> clothingList = mapper.scan(User.class, scanExpression);
        return clothingList;
    }
}
