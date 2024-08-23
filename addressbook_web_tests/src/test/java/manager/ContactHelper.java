package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

    public void openContactPage() {
        if (!manager.isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));
        }
    }

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }


    private void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstName());
        type(By.name("lastname"), contactData.lastName());
        type(By.name("address"), contactData.address());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }


    private void returnToContactPage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    private void submitContactCreation() {
        manager.driver.findElement(By.name("submit")).click();
    }


    public void removeContact(ContactData contact) {
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public int getCount() {

        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {

        var contacts = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.name("entry"));
        for (var entry : entries) {
            var checkbox = entry.findElement(By.cssSelector("input"));
            var id = checkbox.getAttribute("value");
            var lastName = entry.findElement(By.cssSelector("td:nth-child(2)")).getText();

            contacts.add(new ContactData()
                    .withId(id)
                    .withLastName(lastName));


        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactPage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModify();
        returnToContactPage();
    }

    private void initContactModification(ContactData contact) {
        var elementId = manager.driver.findElement(By.id(contact.id()));
        var entry = elementId.findElement(By.xpath("../.."));
        var editButton = entry.findElement(By.cssSelector("td:nth-child(8)"));
        editButton.click();
    }

    private void submitContactModify() {
        click(By.name("update"));
    }


}