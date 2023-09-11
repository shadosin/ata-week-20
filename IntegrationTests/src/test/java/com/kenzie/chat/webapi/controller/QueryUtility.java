package com.kenzie.chat.webapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.chat.webapi.controller.model.CommentCreateRequest;
import com.kenzie.chat.webapi.controller.model.UserCreateRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class QueryUtility {

    public CommentControllerClient commentControllerClient;
    public UserControllerClient userControllerClient;
    public ContentModerationControllerClient contentModerationControllerClient;

    private final MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    public QueryUtility(MockMvc mvc) {
        this.mvc = mvc;
        this.commentControllerClient = new CommentControllerClient();
        this.userControllerClient = new UserControllerClient();
        this.contentModerationControllerClient = new ContentModerationControllerClient();
    }

    public class CommentControllerClient {
        public ResultActions getCommentById(String id) throws Exception {
            return mvc.perform(get("/comment/{id}", id)
                    .accept(MediaType.APPLICATION_JSON));
        }

        public ResultActions getAllComments() throws Exception {
            return mvc.perform(get("/comment/all")
                    .accept(MediaType.APPLICATION_JSON));
        }

        public ResultActions addComment(CommentCreateRequest createRequest) throws Exception {
            return mvc.perform(post("/comment/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(createRequest)));
        }
    }

    public class UserControllerClient {
        public ResultActions getUser(String userName) throws Exception {
            return mvc.perform(get("/user/{username}", userName)
                    .accept(MediaType.APPLICATION_JSON));
        }

        public ResultActions createUser(UserCreateRequest createRequest) throws Exception {
            return mvc.perform(post("/user/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(createRequest)));
        }
    }

    public class ContentModerationControllerClient {
        // Add methods here
    }

}
