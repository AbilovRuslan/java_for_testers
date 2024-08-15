package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("Smth", "Smth", "121609, Anyway, 1 Empty str."));
    }
}