package manager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void create(String mail, String username) {
        openManage();
        accounts();
        click(By.cssSelector(".pull-left .btn-round"));
        type(By.name("username"), username);
        type(By.name("realname"), username);
        type(By.name("email"), mail);
        click(By.cssSelector("[value='Create User']"));
    }



    private void accounts() {
        click(By.linkText("Users"));
    }

    private void openManage() {
        if(!isElementDisplayed(By.cssSelector(".fa-gears")))
        {
            click(By.id("menu-toggler"));
        }
        WebDriverWait wait = new WebDriverWait(manager.driver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-gears")));
        element.click();
    }




}