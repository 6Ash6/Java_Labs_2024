import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderService {

    // Метод для чтения и обработки данных из файла
    public int[] readArrayFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));

        for (String line : lines) {
            try {
                return parseArray(line);  // Пытаемся парсить строку в массив
            } catch (InvalidArrayFormatException e) {
                System.out.println("Invalid array format: " + line);
                // Продолжаем цикл, чтобы обработать следующую строку
            }
        }

        throw new IOException("No valid data found in the file.");
    }

    // Метод для парсинга строки в массив
    private int[] parseArray(String line) throws InvalidArrayFormatException {
        // Проверяем строку на корректность: допустим, она должна содержать только цифры и запятые
        if (!line.matches("^[0-9, -]+$")) {
            throw new InvalidArrayFormatException("Некорректная строка: " + line);
        }

        // Преобразуем строку в массив чисел
        String[] tokens = line.split("[, -]+");  // Разделяем строку по запятым и дефисам
        int[] result = new int[tokens.length];

        try {
            for (int i = 0; i < tokens.length; i++) {
                result[i] = Integer.parseInt(tokens[i].trim());
            }
        } catch (NumberFormatException e) {
            throw new InvalidArrayFormatException("Ошибка преобразования числа: " + line);
        }

        return result;
    }
}

