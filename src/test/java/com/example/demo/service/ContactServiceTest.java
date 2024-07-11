package com.example.demo.service;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setName("John Doe");
        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact));

        List<Contact> contacts = contactService.findAll();

        assertNotNull(contacts);
        assertEquals(1, contacts.size());
        assertEquals("John Doe", contacts.get(0).getName());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setName("John Doe");
        when(contactRepository.findById(1)).thenReturn(Optional.of(contact));

        Optional<Contact> result = contactService.findById(1);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(contactRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveContact() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setName("John Doe");
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact result = contactService.saveContact(contact);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testUpdateContact() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setName("John Doe");
        contact.setEmail("john.doe@example.com");
        contact.setCountry("USA");

        Contact updatedContact = new Contact();
        updatedContact.setId(1);
        updatedContact.setName("Jane Doe");
        updatedContact.setEmail("jane.doe@example.com");
        updatedContact.setCountry("Canada");

        when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(updatedContact);

        Contact result = contactService.updateContact(1, updatedContact);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane.doe@example.com", result.getEmail());
        assertEquals("Canada", result.getCountry());
        verify(contactRepository, times(1)).findById(1);
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(contactRepository).deleteById(1);

        contactService.deleteById(1);

        verify(contactRepository, times(1)).deleteById(1);
    }
}
