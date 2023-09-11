package com.kenzie.chat.usersystem.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;


@DynamoDBTable(tableName = "KenzieChat-Users")
public class User {
    private String username;
    private String email;
    private String name;
    private String joinDate;
    private boolean active;

    public User(){}

    public User(String username, String email, String name, String joinDate, boolean active) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.joinDate = joinDate;
        this.active = active;
    }

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "joinDate")
    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @DynamoDBAttribute(attributeName = "active")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
