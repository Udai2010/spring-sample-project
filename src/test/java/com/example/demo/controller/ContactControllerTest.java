package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void testShowReadContactPage() throws Exception {
        when(contactService.findAll()).thenReturn(Arrays.asList(new Contact(), new Contact()));

        mockMvc.perform(get("/read-contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("readcontact"));

        verify(contactService, times(1)).findAll();
    }

    @Test
    public void testDeleteContact() throws Exception {
        doNothing().when(contactService).deleteById(anyInt());

        mockMvc.perform(get("/delete-contact/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/read-contact"));

        verify(contactService, times(1)).deleteById(anyInt());
    }
}
