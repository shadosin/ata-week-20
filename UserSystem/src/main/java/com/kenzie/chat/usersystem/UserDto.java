package com.kenzie.chat.usersystem;

import java.util.Date;
import java.util.Objects;

public class UserDto {
    private String username;
    private String email;
    private String name;
    private boolean active;

    public UserDto() {
    }

    public UserDto(String username, String email, String name, boolean active) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return active == userDto.active &&
                Objects.equals(username, userDto.username) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(name, userDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, name, active);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
