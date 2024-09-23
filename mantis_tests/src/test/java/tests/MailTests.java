package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.regex.Pattern;


public class MailTests extends TestBase {


    @Test
    public void canDrainInbox() {
        app.mail().drain("user1@localhost", "password");
    }


    @Test
    public void waitForEmail() {
        var messages = app.mail().receive("user1@localhost",
                "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }


    @Test
    public void canExtractUrl() {
        var messages = app.mail().receive("user1@localhost",
                "password", Duration.ofSeconds(10));
        var test = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S+");
        var matcher = pattern.matcher(test);
        if(matcher.find()) {
            var url = test.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }


    @Test
    public void canReceiveEmail() {
        var messages = app.mail().receive("user1@localhost", "password");
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }
}