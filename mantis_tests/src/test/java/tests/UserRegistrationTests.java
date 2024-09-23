package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
public class UserRegistrationTests extends TestBase {

    @ParameterizedTest
    @ValueSource(strings = {"userProvider"})
    public void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);

        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        // заполняем форму создания и отправляем (браузер)
        // ждём почту (MailHelper)
        // извлекаем ссылку из письма
        // проходим по ссылке и завершаем регистрацию (браузер)
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)

    }
}
