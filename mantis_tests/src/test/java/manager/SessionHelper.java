package manager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class SessionHelper extends  HelperBase {

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@type='submit']"));
    }



    public void loginAsAdmin(){
        login(manager.property("web.username"), manager.property("web.password"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void logout() {
        click(By.cssSelector(".user-info"));
        click(By.cssSelector(".fa-sign-out"));
    }
}