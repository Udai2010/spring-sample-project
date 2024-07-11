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
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ContactControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void testShowReadContactPage() throws Exception {
        when(contactService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/read-contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("readcontact"))
                .andExpect(model().attributeExists("contacts"));

        verify(contactService, times(1)).findAll();
    }

    @Test
    public void testCreateContact() throws Exception {
        Contact contact = new Contact();
        mockMvc.perform(post("/create-contact")
                        .flashAttr("contact", contact))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/read-contact"));

        verify(contactService, times(1)).saveContact(contact);
    }

}
