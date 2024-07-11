package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {

    @Test
    public void testGettersAndSetters() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setName("John Doe");
        contact.setEmail("john.doe@example.com");
        contact.setCountry("USA");

        assertEquals(1, contact.getId());
        assertEquals("John Doe", contact.getName());
        assertEquals("john.doe@example.com", contact.getEmail());
        assertEquals("USA", contact.getCountry());
    }
}
