package generator;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generator {

        @Parameter(names = {"--type", "-t"}, variableArity = true)
        List<String> types; // Список типов (например, contacts, groups)

        @Parameter(names = {"--output", "-o"})
        String output;

        @Parameter(names = {"--format", "-f"}, variableArity = true)
        List<String> formats; // Список форматов

        @Parameter(names = {"--count", "-c"})
        int count;


    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        // Если types не был задан, выбрасываем ошибку
        if (types == null || types.isEmpty()) {
            throw new IllegalArgumentException("Необходимо указать хотя бы один тип.");
        }

        // Проверяем каждый элемент списка types
        for (String type : types) {
            if ("contacts".equals(type)) {
                var data = generateContact(); // Генерируем контакты
                save(data); // Сохраняем контакты
            } else if ("groups".equals(type)) {
                var data = generateGroups(); // Генерируем группы
                save(data); // Сохраняем группы
            } else {
                throw new IllegalArgumentException("Неизвестный тип: " + type);
            }
        }
    }

    private void save(Object data) throws IOException {
        for (String format : formats) {
            if ("json".equals(format)) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(new File(output), data);
            } else {
                throw new IllegalArgumentException("Неизвестный формат: " + format);
            }
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