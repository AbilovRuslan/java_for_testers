package generator;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;


    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();

    }

    private void run() {
        var data = generate();
        save(data);
    }

    private void save(Object data) {
    }

    private Object generate() {
        if ("contacts".equals(type)) {
            return generateContact();
        } else if ("groups".equals(type)) {
            return generateGroups();
        } else {
            throw new IllegalArgumentException("Неизвестное что-то" + type);
        }
    }



    private Object generateContact() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            result.add(new ContactData()
                    .withLastName(CommonFunctions.randomString(i * 10))
                    .withFirstName(CommonFunctions.randomString(i * 10))
                    .withAddress(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }



    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

}

