package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
    

    @Test
    void canRemoveAllContactsAtOnce(){
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("", "Smth", "Smth", "121609, Anyway, 1 Empty str."));
        }
        app.contacts() .removeAllContact();
        Assertions.assertEquals(0,app.contacts().getCount());

    }
}