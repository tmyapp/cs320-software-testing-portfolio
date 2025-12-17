import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    public Contact addContact(String id, String firstName, String lastName,
                              String phone, String address) {

        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate id");
        }
        Contact c = new Contact(id, firstName, lastName, phone, address);
        contacts.put(id, c);
        return c;
    }

    public void deleteContact(String id) {
        if (!contacts.containsKey(id)) {
            throw new IllegalArgumentException("Id not found");
        }
        contacts.remove(id);
    }

    private Contact getContactOrThrow(String id) {
        Contact c = contacts.get(id);
        if (c == null) {
            throw new IllegalArgumentException("Id not found");
        }
        return c;
    }

    public Contact getContact(String id) {
        return getContactOrThrow(id);
    }

    public void updateFirstName(String id, String firstName) {
        getContactOrThrow(id).setFirstName(firstName);
    }

    public void updateLastName(String id, String lastName) {
        getContactOrThrow(id).setLastName(lastName);
    }

    public void updatePhone(String id, String phone) {
        getContactOrThrow(id).setPhone(phone);
    }

    public void updateAddress(String id, String address) {
        getContactOrThrow(id).setAddress(address);
    }
}
