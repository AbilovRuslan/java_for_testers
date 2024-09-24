package tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Test;


public class JamesTest extends TestBase {

    @Test
    public void canCreateUser() {
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(5)), "password");
    }
}