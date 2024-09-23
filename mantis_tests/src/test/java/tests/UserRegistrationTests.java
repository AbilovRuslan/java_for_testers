package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
public class UserRegistrationTests extends TestBase {

    @ParameterizedTest
    @ValueSource(strings = {"userProvider"})
    public void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);

    }
}
