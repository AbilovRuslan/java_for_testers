package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void removeContact() {
        openHomePage();
        selectContact();
        deleteSelectedContact();
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    private void selectContact() {
        click(By.xpath("(//input[@name=\"selected[]\"])[1]"));
    }

    private void deleteSelectedContact() {
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
}