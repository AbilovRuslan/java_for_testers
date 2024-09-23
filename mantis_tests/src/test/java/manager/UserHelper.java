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