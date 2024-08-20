package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Smth", "Smth", "121609, Anyway, 1 Empty str.","chi"));
        }
        app.contacts().removeContact();
    }


}