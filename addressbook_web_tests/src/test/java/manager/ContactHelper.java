package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openHomePage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        deleteSelectedContacts();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input [value = '%s']", contact.id())));
    }

    private void deleteSelectedContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
    }

    private void submitContactCreation() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    private void openHomePage() {
        click(By.linkText("home"));
        timeout(1);
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContact() {
        openHomePage();
        selectAllContacts();
        deleteSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var spans = manager.driver.findElements(By.cssSelector("span.contact"));
        for (var span : spans){
            var name = span.getText();
            var checkbox = span. findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(name));
        }
        return contacts;

    }
}   