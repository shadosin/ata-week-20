package com.kenzie.chat.webapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.chat.usersystem.UserDto;
import com.kenzie.chat.usersystem.UserService;
import com.kenzie.chat.webapi.IntegrationTest;
import com.kenzie.chat.webapi.controller.model.CommentCreateRequest;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;
import com.kenzie.chat.webapi.service.ContentModerationService;
import net.andreinc.mockneat.MockNeat;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Use this class to create your integration tests for the ContentModerationController
@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentModerationControllerTest {
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;
    private ContentModerationService contentModerationService;

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }

    @Test
    public void testCheckForSpam() throws Exception {
        // Create a list of users with known spam accounts
        List<UserDto> usersWithSpam = new ArrayList<>();
        usersWithSpam.add(new UserDto());
        usersWithSpam.add(new UserDto());
        // Mock the contentModerationService to return the list of deactivated accounts
        queryUtility.contentModerationControllerClient.checkForSpam(usersWithSpam)
                .andExpect(status().isOk());
    }

}
