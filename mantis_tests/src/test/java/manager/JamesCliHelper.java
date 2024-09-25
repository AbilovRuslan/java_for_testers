package manager;


import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

import java.util.Map;

import static tests.TestBase.app;


public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager app) {
        super(app);
    }

    public void addUser(String username, String password) {
        CommandLine cmd = new CommandLine("java", "-cp", "james-server-jpa-app.lib/*",
                "org.apache.james.cli.ServerCmd",
                "AddUser", username, password);
        cmd.setWorkingDirectory(app.prop("james.dir"));
        CircularOutputStream out = new CircularOutputStream();
        cmd.copyOutputTo(out);
        cmd.execute();
        cmd.waitFor();
        System.out.println(out);
    }

    public void addUser(Map<String, String> user) {
    }
}