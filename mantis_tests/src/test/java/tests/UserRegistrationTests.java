package tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tests.TestBase.app;


// создать пользователя (адрес) на почтовом сервере (JamesHelper) - есть!
// заполняем форму создания и отправляем (браузер) -есть!
// ждём почту (MailHelper)-есть!recieve c duration
// извлекаем ссылку из письма -есть!
// проходим по ссылке и завершаем регистрацию (браузер) -есть!
// проверяем, что пользователь может залогиниться (HttpSessionHelper) -есть!

public class UserRegistrationTests extends TestBase {

    // Метод, возвращающий список пользователей с произвольными именами и адресами электронной почты
    public static List<Map<String, String>> userProvider() {
        var result = new ArrayList<Map<String, String>>(); // Инициализируем пустой список
        // Генерируем 3 пользователя
        for (var i = 0; i < 3; i++) {
            String name = CommonFunctions.randomString(5); // Генерируем случайное имя из 5 символов
            Map<String, String> user = new HashMap<>(); // Создаем новую карту для пользователя
            user.put("name", name); // Добавляем имя
            user.put("email", String.format("%s@localhost", name)); // Формируем электронный адрес на основе имени
            user.put("password", "password"); // Фиксированный пароль для пользователей
            result.add(user); // Добавляем пользователя в список
        }
        return result; // Возвращаем список пользователей
    }

    // Определяем параметризованный тест, использующий метод `userProvider`
    @ParameterizedTest
    @MethodSource("userProvider")
    void canRegisterUser(Map<String, String> user) {
        // Шаг 1: Добавляем пользователя на почтовом сервере
        app.jamesCli().addUser(user);
        // Шаг 2: Логинимся как администратор
        app.session().loginAsAdmin();
        // Шаг 3: Создаем пользователя в приложении
        app.user().create(user);
        // Шаг 4: Получаем ссылку для завершения регистрации из почты
        var url = app.mail().getUrl(user.toString());
        // Шаг 5: Открываем ссылку в браузере для завершения регистрации
        app.driver().get(url);
        // Шаг 6: Завершаем процесс создания пользователя
        app.user().finishCreation(user.toString());
        // Шаг 7: Логинимся в приложении с учетными данными нового пользователя
        app.http().login(user);
        // Шаг 8: Проверяем, что пользователь успешно вошел в систему
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}