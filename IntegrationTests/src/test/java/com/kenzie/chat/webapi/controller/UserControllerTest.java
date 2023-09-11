package com.kenzie.chat.webapi.controller;

import com.kenzie.chat.webapi.IntegrationTest;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;

import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }


    @Test
    public void can_create_user() throws Exception {
        // GIVEN
        UserCreateRequest createRequest = new UserCreateRequest();
        createRequest.setUsername(mockNeat.strings().get());
        createRequest.setName(mockNeat.names().get());
        createRequest.setEmail(mockNeat.emails().get());

        // WHEN
        queryUtility.userControllerClient.createUser(createRequest)
        // THEN
                .andExpect(status().isOk());
    }

    @Test
    public void can_get_user() throws Exception {
        // GIVEN
        UserCreateRequest createRequest = new UserCreateRequest();
        createRequest.setUsername(mockNeat.strings().get());
        createRequest.setName(mockNeat.names().get());
        createRequest.setEmail(mockNeat.emails().get());

        queryUtility.userControllerClient.createUser(createRequest)
                .andExpect(status().isOk());

        // WHEN
        queryUtility.userControllerClient.getUser(createRequest.getUsername())
        // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("username")
                        .value(is(createRequest.getUsername())))
                .andExpect(jsonPath("email")
                        .value(is(createRequest.getEmail())))
                .andExpect(jsonPath("name")
                        .value(is(createRequest.getName())))
                .andExpect(jsonPath("active")
                        .value(is(true)));
    }
}