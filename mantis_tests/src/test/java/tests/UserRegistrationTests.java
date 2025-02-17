package tests;

import common.CommonFunctions;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


// создать пользователя (адрес) на почтовом сервере (JamesHelper) - есть!
// заполняем форму создания и отправляем (браузер) -есть!
// ждём почту (MailHelper)-есть!recieve c duration
// извлекаем ссылку из письма -есть!
// проходим по ссылке и завершаем регистрацию (браузер) -есть!
// проверяем, что пользователь может залогиниться (HttpSessionHelper) -есть!

public class UserRegistrationTests extends TestBase {

    public static List<UserData> userProvider() {
        var result = new ArrayList<UserData>();
        for (var i = 0; i < 1; i++) {
            String name = CommonFunctions.randomString(5);
            result.add(new UserData()
                    .withName(name)
                    .withEmail(String.format("%s@localhost", name))
                    .withPassword("password"));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void canRegisterUser(UserData userData) {
        app.jamesCli().addUser(userData);
        app.session().loginAsAdmin();
        app.user().create(userData);
        var url = app.mail().getUrl(userData);
        app.driver().get(url);
        app.user().finishCreation(userData);
        app.http().login(userData);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void canRegisterUserByApi(UserData userData) {
        app.jamesApi().addUser(userData);
        app.session().loginAsAdmin();
        app.user().create(userData);
        var url = app.mail().getUrl(userData);
        app.driver().get(url);
        app.user().finishCreation(userData);
        app.http().login(userData);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void canRegisterUserByRest(UserData userData) {
        app.jamesApi().addUser(userData); //регистрирует новый адрес на почтовом сервере James, используя REST API.
        app.rest().createUser(userData);//регистрацию нового пользователя в Mantis, используя REST API.
        var url = app.mail().getUrl(userData);
        app.driver().get(url);
        app.user().finishCreation(userData);
        app.http().login(userData);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    UserData userMail;
    @Test
    void canRegisterUserDeveloperMail() {
        userMail = app.developerMail().addUser();
        var mail = String.format("%s@developermail.com", userMail.name());

        userMail = userMail.withEmail(mail).withPassword("password");
        app.session().loginAsAdmin();
        app.user().create(userMail);
        System.out.println(userMail);

        var message = app.developerMail().receive(userMail, Duration.ofSeconds(100));
        var url = CommonFunctions.extractUrl(message);

        app.driver().get(url);
        app.user().finishCreation(userMail);
        app.http().login(userMail);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

  
}