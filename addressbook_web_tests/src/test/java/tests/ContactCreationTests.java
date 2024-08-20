package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {
    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("Smth", "Smth", "121609, Anyway, 1 Empty str.","chi"));
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        app.contacts().createContact(contact);
    }

    private static Stream<ContactData> contactProvider() {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {  // Генерируем 3 контакта, поставлю 20 -будет 20
            contacts.add(generateRandomContact());
        }
        return contacts.stream();
    }

    private static ContactData generateRandomContact() {
        Random random = new Random();
        String firstName = "FirstName" + random.nextInt(20);  // 20 число для иннициализации набора тестовых данных
        String lastName = "LastName" + random.nextInt(20);
        String address = random.nextInt(20) + " Random St, City, Country";
        String nickName = random.nextInt(20) + " Random St, City, Country";

        // и тд остальные окна по тому же принципу.

        return new ContactData(firstName, lastName, address,nickName);
    }

}