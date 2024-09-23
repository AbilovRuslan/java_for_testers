package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



// создать пользователя (адрес) на почтовом сервере (JamesHelper)
// заполняем форму создания и отправляем (браузер)
// ждём почту (MailHelper)
// извлекаем ссылку из письма
// проходим по ссылке и завершаем регистрацию (браузер)
// проверяем, что пользователь может залогиниться (HttpSessionHelper)

public class UserRegistrationTests extends TestBase {
    //video 8.6
    @ParameterizedTest
    @ValueSource(strings = {"user1"})
    public void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
    }
}