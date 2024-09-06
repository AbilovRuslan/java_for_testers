package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canContactRemoval() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "Smth", "Smth", "121609, Anyway, 1 Empty str."));
        }
        var oldContacts = app.contacts().getList();
        var random = new Random();
        var index = random.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemovalAllContacts() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName("Smth")
                    .withLastName("Smth")
                    .withAddress("121609, Anyway, 1 Empty str"));

        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}