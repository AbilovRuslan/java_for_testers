package manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private Properties prop;
    private SessionHelper session;
    private HttpSessionHelper http;
    private JamesCliHelper james;
    private MailHelper mail;
    private UserHelper userHelper;
    private Properties properties;
    private String string;
    private String password;


    public WebDriver driver() {
        if(driver == null) {
            if("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            }else {
                throw new IllegalArgumentException(String.format("Unrecognized browser: %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(prop.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1264, 964));
        }
        return driver;
    }

    public SessionHelper session() {
        if(session == null) {
            session = new SessionHelper(this);
        }
        return session;
    }

    public HttpSessionHelper http() {
        if(http == null) {
            http = new HttpSessionHelper(this);
        }
        return http;
    }

    public String prop(String key) {
        return prop.getProperty(key);
    }

    public JamesCliHelper jamesCli() {
        if(james == null) {
            james = new JamesCliHelper(this);
        }
        return james;
    }

    public UserHelper user() {
        if (userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MailHelper mail() {
        if(mail == null) {
            mail = new MailHelper(this);
        }
        return mail;
    }



    public  UserHelper userHelper() {
        if(userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
    }


    public String property(String name) {
        return properties.getProperty(name);
    }
}