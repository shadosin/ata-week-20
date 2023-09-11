package com.kenzie.chat.webapi.config;

import com.kenzie.chat.usersystem.UserService;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class UserServiceConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserService getUserService() {
        return new UserService();
    }
}
