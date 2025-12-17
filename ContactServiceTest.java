import org.junit.Test;
import static org.junit.Assert.*;

public class ContactServiceTest {

    private void assertThrows(Class<? extends Throwable> expected, Runnable executable) {
        try {
            executable.run();
            fail("Expected exception of type " + expected.getName());
        } catch (Throwable actual) {
            assertTrue(
                "Expected " + expected.getName() + " but got " + actual.getClass().getName(),
                expected.isInstance(actual)
            );
        }
    }

    @Test
    public void addContactStoresContact() {
        ContactService service = new ContactService();
        service.addContact("1", "John", "Smith", "1234567890", "123 Main St");
        Contact c = service.getContact("1");
        assertEquals("John", c.getFirstName());
    }

    @Test
    public void addDuplicateIdThrows() {
        ContactService service = new ContactService();
        service.addContact("1", "John", "Smith", "1234567890", "123 Main St");

        assertThrows(IllegalArgumentException.class, () ->
            service.addContact("1", "Jane", "Doe", "0987654321", "456 Oak Ave"));
    }

    @Test
    public void deleteContactRemovesIt() {
        ContactService service = new ContactService();
        service.addContact("1", "John", "Smith", "1234567890", "123 Main St");
        service.deleteContact("1");

        assertThrows(IllegalArgumentException.class, () -> service.getContact("1"));
    }

    @Test
    public void deleteUnknownIdThrows() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("1"));
    }

    @Test
    public void updateFieldsChangeValues() {
        ContactService service = new ContactService();
        service.addContact("1", "John", "Smith", "1234567890", "123 Main St");

        service.updateFirstName("1", "Jane");
        service.updateLastName("1", "Doe");
        service.updatePhone("1", "0987654321");
        service.updateAddress("1", "456 Oak Ave");

        Contact c = service.getContact("1");
        assertEquals("Jane", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());
    }

    @Test
    public void updateUnknownIdThrows() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("1", "Jane"));
    }
}