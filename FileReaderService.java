import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderService {
    private final ArrayValidationService validationService = new ArrayValidationService();

    public Array readArrayFromFile(String filePath) throws InvalidArrayFormatException, IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));

        for (String line : lines) {
            if (validationService.isValidArrayString(line)) {
                int[] arrayElements = validationService.parseStringToArray(line);
                return new Array(arrayElements);
            } else {
                System.out.println("Некорректная строка: " + line);
            }
        }

        throw new InvalidArrayFormatException("Не найдено ни одной корректной строки для создания массива.");
    }
}
