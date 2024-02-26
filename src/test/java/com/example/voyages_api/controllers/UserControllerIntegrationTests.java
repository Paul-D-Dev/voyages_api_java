package com.example.voyages_api.controllers;

import com.example.voyages_api.TestDataUtil;
import com.example.voyages_api.domain.entities.UserEntity;
import com.example.voyages_api.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {

    private final UserService service;

    private final MockMvc mvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public UserControllerIntegrationTests(MockMvc mvc, UserService service) {
        this.service = service;
        this.mvc = mvc;
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateUserSuccessfullyReturnsHttp201Created() throws Exception {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        user.setId(null);
        String userJson = objectMapper.writeValueAsString(user);

        mvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateUserSuccessfullyReturnsUserSaved() throws Exception {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        user.setId(null);
        String userJson = objectMapper.writeValueAsString(user);

        mvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Georges")
        );
    }

    @Test
    public void TestThatFoundUserSuccessfullyReturnsHttpStatus200WhenUserExists() throws Exception {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        service.create(user);

        mvc.perform(
                MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        service.create(user);

        mvc.perform(
                MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Georges")
        );
    }

    @Test
    public void TestThatFoundUserFailedAndReturnsHttpStatus404WhenNoUserExists() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/users/403")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
}
