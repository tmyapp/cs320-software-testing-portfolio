import org.junit.Test;
import static org.junit.Assert.*;

public class ContactTest {
    
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
    public void validContactIsCreated() {
        Contact c = new Contact("1", "John", "Smith", "1234567890", "123 Main St");
        assertEquals("1", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    public void idTooLongOrNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "John", "Smith", "1234567890", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "John", "Smith", "1234567890", "123 Main St"));
    }

    @Test
    public void firstNameInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "FirstnameTooLong", "Smith", "1234567890", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", null, "Smith", "1234567890", "123 Main St"));
    }

    @Test
    public void lastNameInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "LastnameTooLong", "1234567890", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", null, "1234567890", "123 Main St"));
    }

    @Test
    public void phoneInvalidThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Smith", "12345", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Smith", "abcdefghij", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Smith", null, "123 Main St"));
    }

    @Test
    public void addressInvalidThrows() {
        String longAddress = "1234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Smith", "1234567890", longAddress));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Smith", "1234567890", null));
    }

    @Test
    public void settersUpdateAndValidate() {
        Contact c = new Contact("1", "John", "Smith", "1234567890", "123 Main St");

        c.setFirstName("Jane");
        c.setLastName("Doe");
        c.setPhone("0987654321");
        c.setAddress("456 Oak Ave");

        assertEquals("Jane", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));
    }
}