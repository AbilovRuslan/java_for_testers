package tests;

import common.CommonFunctions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tests.TestBase.app;


// создать пользователя (адрес) на почтовом сервере (JamesHelper)
// заполняем форму создания и отправляем (браузер)
// ждём почту (MailHelper)
// извлекаем ссылку из письма
// проходим по ссылке и завершаем регистрацию (браузер)
// проверяем, что пользователь может залогиниться (HttpSessionHelper)

public class UserRegistrationTests extends TestBase {

    public static List<Map<String, String>> userProvider() {
        var result = new ArrayList<Map<String, String>>();
        for (var i = 0; i < 3; i++) {
            String name = CommonFunctions.randomString(5);
            Map<String, String> user = new HashMap<>();
            user.put("name", name);
            user.put("email", String.format("%s@localhost", name));
            user.put("password", "password");
            result.add(user);
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void canRegisterUser(Map<String, String> user) {
        app.jamesCli().addUser(user.get("email"), user.get("password"));
        app.session().loginAsAdmin();
        app.user().create(user.get("name"), user.get("email"), user.get("password"));
        var url = app.mail().getUrl(user.get("email"));
        app.driver().get(url);
        app.user().setPassword(user.get("password"));
        app.http().login(user.get("email"), user.get("password"));
        app.http().isLoggedIn();
    }
}