import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertNotNull(service.getContact("1"));
    }

    @Test
    public void testAddContactDuplicateID() {
        Contact contact1 = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact1);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            service.addContact(contact1));
        assertEquals("Contact already exists or is invalid", exception.getMessage());
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.deleteContact("1");
        assertNull(service.getContact("1"));
    }

    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateContact("1", "Jane", "Doe", "0987654321", "456 Main St");
        Contact updatedContact = service.getContact("1");
        assertAll("update",
            () -> assertEquals("Jane", updatedContact.getFirstName()),
            () -> assertEquals("Doe", updatedContact.getLastName()),
            () -> assertEquals("0987654321", updatedContact.getPhone()),
            () -> assertEquals("456 Main St", updatedContact.getAddress())
        );
    }

}
