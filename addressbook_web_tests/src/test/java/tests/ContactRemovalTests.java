package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canContactRemoval() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "First Name",
                    "Middle Name",
                    "Last Name",
                    "Nick Name",
                    "src/test/resources/images/file.png",
                    "Title",
                    "Company",
                    "Address",
                    "767676765",
                    "9884433t55",
                    "543468765",
                    "52283444",
                    "",
                    "email1@gmail.com",
                    "email2@rmail.com",
                    "email3@amail.com",
                    "",
                    "HomePage",
                    "23",
                    "June",
                    "1996",
                    "44",
                    "July",
                    "2024"));
        }
        var oldContacts = app.hbm().getContactList();
        var random = new Random();
        var index = random.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
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