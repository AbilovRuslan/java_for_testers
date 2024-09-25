package manager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Map;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void create(String email, String username) {
        openManage();
        accounts();
        click(By.cssSelector(".pull-left .btn-round"));
        type(By.name("username"), username);
        type(By.name("realname"), username);
        type(By.name("email"), email);
        click(By.cssSelector("[value='Create User']"));
    }

    public void create(String email, String username, String password) {
        create(email, username);
        finishCreation(password, password); // передаем password и confirmPassword
    }



    private void accounts() {
        click(By.linkText("Users"));
    }

    private void openManage() {
        if (!isElementDisplayed(By.cssSelector(".fa-gears"))) {
            click(By.id("menu-toggler"));
        }
        WebDriverWait wait = new WebDriverWait(manager.driver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-gears")));
        element.click();
    }

    public void finishCreation(String password, String confirmPassword) {
        type(By.id("password"), password);
        type(By.id("password-confirm"), confirmPassword);
        click(By.className("btn-success")); 
    }

    public void create(Map<String, String> user) {
        String email = user.get("email");
        String username = user.get("username");
        String password = user.get("password");
        create(email, username, password); // используем новый метод create
    }
}