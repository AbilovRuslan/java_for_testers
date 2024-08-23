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
            // Проверяем, есть ли существующие контакты, если нет, создаем новый
            if (app.contacts().getCount() == 0) {
                app.contacts().createContact(new ContactData()
                        .withFirstName("FirstName")
                        .withLastName("LastName")
                        .withAddress("Address"));
            }

            // Получаем текущий список контактов
            var oldContacts = app.contacts().getList();
            var rnd = new Random();
            // Выбираем случайный индекс для изменения контакта
            var index = rnd.nextInt(oldContacts.size());
            var modifiedContact = new ContactData()
                    .withFirstName("ModifiedFirstName")
                    .withLastName("ModifiedLastName")
                    .withAddress("Modified Address");

            // Модифицируем выбранный контакт
            app.contacts().modifyContact(oldContacts.get(index), modifiedContact);

            // Получаем новый список контактов после изменений
            var newContacts = app.contacts().getList();

            // Создаем ожидаемый список, где изменяем только модифицированный контакт
            var expectedList = new ArrayList<ContactData>(oldContacts);
            expectedList.set(index, modifiedContact.withId(oldContacts.get(index).id()));

            // Сортируем списки по ID для корректного сравнения
            Comparator<ContactData> compareById = (o1, o2) -> {
                return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            };
            newContacts.sort(compareById);
            expectedList.sort(compareById);


            // Проверяем, что новые контакты совпадают с ожидаемыми ( и они не совпадают)))
            Assertions.assertEquals(newContacts, expectedList);
        }
}