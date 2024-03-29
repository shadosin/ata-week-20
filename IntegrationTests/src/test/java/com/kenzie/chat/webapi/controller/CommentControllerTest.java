package com.kenzie.chat.webapi.controller;

import com.kenzie.chat.webapi.IntegrationTest;
import com.kenzie.chat.webapi.controller.model.CommentCreateRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }

    @Test
    public void can_create_comment() throws Exception {
        // Fix this test
        UserCreateRequest createRequest = new UserCreateRequest();
        createRequest.setUsername(mockNeat.strings().get());
        createRequest.setName(mockNeat.names().get());
        createRequest.setEmail(mockNeat.emails().get());
        queryUtility.userControllerClient.createUser(createRequest);

        // GIVEN
        CommentCreateRequest commentRequest = new CommentCreateRequest();
        commentRequest.setOwner(createRequest.getUsername());
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());

        // WHEN
        queryUtility.commentControllerClient.addComment(commentRequest)
        // THEN
                .andExpect(status().isOk());
    }

    // Add additional tests here
    @Test
    public void nullUser_throws_exception() throws Exception{
        UserCreateRequest createRequest = new UserCreateRequest();
        createRequest.setUsername(mockNeat.strings().get());
        createRequest.setName(mockNeat.names().get());
        createRequest.setEmail(mockNeat.emails().get());
        queryUtility.userControllerClient.createUser(createRequest);

        // GIVEN
        CommentCreateRequest commentRequest = new CommentCreateRequest();
        commentRequest.setOwner(null);
        commentRequest.setTitle(mockNeat.strings().get());
        commentRequest.setContent(mockNeat.strings().get());

        // WHEN
        Assertions.assertThrows(Exception.class, () -> {
            queryUtility.commentControllerClient.addComment(commentRequest);
        });
    }

}

