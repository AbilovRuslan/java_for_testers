package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("AddedFirstName")
                    .withLastName("AddedLastName")
                    .withAddress("Added address")
                    .withEmails("test21211@mail.com", "testy2@mail.com", "testII@mail.com")
                    .withPhones("333333", "+323232323232", "7655323", "4438888214"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData("", "First Name",
                "Modify Middle Name",
                "Modify Last Name",
                "Modify Nick Name",
                "src/test/resources/images/file5.jpg",
                "Modify Title",
                "ModifyCompany",
                "ModifyAddress",
                "7342423575751",
                "780055535352",
                "790234242345353",
                "760053242342345535354",
                "",
                "email1@mail.com",
                "email2@mail.com",
                "email3@mail.com",
                "",
                "MyHomePage",
                "22",
                "June",
                "2020",
                "11",
                "July",
                "2025");
        app.contacts().modifyContact(oldContacts.get(index),testData);
        //app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<ContactData>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}