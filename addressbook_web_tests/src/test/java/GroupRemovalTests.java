import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GroupRemovalTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost/addressbook/");
        driver.manage().window().setSize(new Dimension(1440, 815));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.name("pass")).sendKeys(Keys.ENTER);

    }

    @AfterEach
    public void tearDown() {
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();


    }

    @Test
    public void canRemoveGroup() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
        //если нет групп, создаем группу, а потом по скрипту сносим
        if (!isElementPresent(By.name("selected[]"))) {
            driver.findElement(By.linkText("groups")).click();
            driver.findElement(By.name("new")).click();
            driver.findElement(By.name("group_name")).click();
            driver.findElement(By.name("group_name")).sendKeys("group name");
            driver.findElement(By.name("group_header")).click();
            driver.findElement(By.name("group_header")).sendKeys("group header");
            driver.findElement(By.name("group_footer")).click();
            driver.findElement(By.name("group_footer")).sendKeys("group footer");
            driver.findElement(By.name("submit")).click();
            driver.findElement(By.linkText("group page")).click();
        }

        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();

    }

    private boolean isElementPresent(By Locator) {
        try {
            driver.findElement(Locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;

        }

    }

}
