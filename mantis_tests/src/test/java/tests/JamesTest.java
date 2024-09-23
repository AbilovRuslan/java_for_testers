package tests;

import org.junit.jupiter.api.Test;
import common.CommonFunctions;

public class JamesTest extends TestBase{

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctions.randomString(8)),
                "password");
    }
}