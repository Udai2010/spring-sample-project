package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DefaultControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DefaultController defaultController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(defaultController).build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/v1/index"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }
}
