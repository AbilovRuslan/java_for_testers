package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "name", "", "")));
        for (var name : List.of("", "name")) {
            for (var firstName : List.of("", "firstName")) {
                for (var address : List.of("", "address")) {
                    result.add(new ContactData().withFirstName(firstName).withLastName(name).withAddress(address));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withAddress(randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {

        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
        ;

    }
}