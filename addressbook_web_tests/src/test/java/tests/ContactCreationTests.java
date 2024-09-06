package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.trim;

public class ContactCreationTests extends TestBase {

    public ContactCreationTests() throws IOException {
    }

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var lastName : List.of("", "Smth")) {
            for (var firstName : List.of("", "Smth")) {
                for (var address : List.of("", "121609, Anyway, 1 Empty str")) {
                    result.add(new ContactData()
                            .withLastName(lastName)
                            .withFirstName(firstName)
                            .withAddress(address));
                }
            }
        }


    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
    result.addAll(value);
        return result;
    }



    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.jdbc().getContactsList();
        app.contacts().createContact(contact);
        var newContact = app.jdbc().getContactsList();

        // компаратор для сортировки по (id) в порядке возрастания
        Comparator<ContactData> compareById = (o1, o2) -> {
            // Сравниваем значения идентификаторов двух объектов. строки в числа для сравнения
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById); // Сортируем список newContact по id в Новом списке
        var expectedList = new ArrayList<>(oldContacts); // Создаем ожидаемый список, основанный на сохраненном старом списке контактов
        expectedList.add(contact.withId(newContact.get(newContact.size() - 1).id())
                .withLastName(contact.lastName())
                .withFirstName(contact.firstName())
                .withAddress(contact.address())); // Добавляем новый контакт в ожидаемый список и устанавливаем его ID- как ID последнего контакт из нового списка

        expectedList.sort(compareById); // Сортируем ожидаемый список по идентификаторам, чтобы он соответствовал порядку нового списка
        Assertions.assertEquals(newContact, expectedList); // Сравниваем фактический и ожидаемый списки на совпадение. И он не совпадает)))
    }

    public static List<ContactData> negativeContactProvider() {
        return List.of(
                new ContactData().withFirstName("FirstName'"),
                new ContactData().withLastName("LastName'")
        );
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContact = app.contacts().getList();

        Assertions.assertEquals(oldContacts, newContact);
    }
}