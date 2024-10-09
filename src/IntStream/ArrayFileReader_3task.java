package IntStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayFileReader_3task {
    // Чтение массива из файла и его валидация
    public int[] readArrayFromFile(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines.filter(this::isValidArray)
                    .findFirst()
                    .map(this::parseArray)
                    .orElseThrow(() -> new IOException("No valid data found"));
        }
    }

    // Валидация строки для создания массива
    private boolean isValidArray(String line) {
        return line.matches("^[0-9,\\s-]+$"); // Разрешаем цифры, запятые, пробелы и дефисы
    }

    // Парсинг строки в массив
    private int[] parseArray(String line) {
        return Arrays.stream(line.split("[,\\s-]+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    // Сортировка массива с использованием IntStream
    public int[] sortArray(int[] array) {
        return IntStream.of(array).sorted().toArray();
    }

    // Сортировка массива в обратном порядке
    public int[] reverseSortArray(int[] array) {
        return IntStream.of(array).boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // Сортировка массива методом пузырька (ручная реализация)
    public int[] bubbleSort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j] > result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }
}

