package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
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
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(
                    "",
                    randomString(i * 5),
                    randomString(i * 5),
                    randomString(i * 5)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContact = app.contacts().getList();

//  компаратор для сортировки по (id) в порядке возрастания
        Comparator<ContactData> compareById = (o1, o2) -> {
            //Сравниваем значения идентификаторов двух объектов. строки в числа для  сравнения
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById); //Сортируем список newContact по id в Новом списке
        var expectedList = new ArrayList<>(oldContacts); // Создаем ожидаемый список, основанный на сохраненном старом списке контактов
        expectedList.add(contact.withId(newContact.get(newContact.size() - 1).id())
                .withLastName("")
                .withFirstName("")
                .withAddress("")); //Добавляем новый контакт в ожидаемый список и устанавливаем его ID- как ID последнего контакт из нового списка
        expectedList.sort(compareById);// // Сортируем ожидаемый список по идентификаторам, чтобы он соответствовал порядку нового списка
        Assertions.assertEquals(newContact, expectedList); //Сравниваем фактический и ожидаемый списки на совпадение. И он не совпадает)))

    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(
                new ContactData().withFirstName("FirstName'"),
                new ContactData().withLastName("LastName'")));
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