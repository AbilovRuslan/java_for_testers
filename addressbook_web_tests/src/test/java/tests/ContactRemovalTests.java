package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("Smth", "Smth", "121609, Anyway, 1 Empty str."));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount -1, newContactCount);
    }

    @Test
    void canRemoveAllContactsAtOnce(){
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("Smth", "Smth", "121609, Anyway, 1 Empty str."));
        }
        app.contacts() .removeAllContact();
        Assertions.assertEquals(0,app.contacts().getCount());

    }
}