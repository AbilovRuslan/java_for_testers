package tests;

import model.ContactData;
import model.GroupData;
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
                    "src/test/resources/images/file2.png",
                    "Title",
                    "Company",
                    "Address",
                    "757575",
                    "78005553535",
                    "79005553535",
                    "76005553535",
                    "",
                    "email1@mail.com",
                    "email2@mail.com",
                    "email3@mail.com",
                    "",
                    "MyHomePage",
                    "20",
                    "May",
                    "1990",
                    "15",
                    "December",
                    "2012"));
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


}