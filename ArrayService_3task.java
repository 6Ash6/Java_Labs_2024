import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ArrayService_3task {
    // Поиск минимального значения в массиве
    public OptionalInt findMin(int[] array) {
        return IntStream.of(array).min();
    }

    // Поиск максимального значения в массиве
    public OptionalInt findMax(int[] array) {
        return IntStream.of(array).max();
    }

    // Замена элементов массива, если элемент удовлетворяет условию (например, заменим все отрицательные числа на 0)
    public int[] replaceIf(int[] array, int oldValue, int newValue) {
        return IntStream.of(array)
                .map(val -> val == oldValue ? newValue : val)
                .toArray();
    }

    // Определение среднего значения элементов массива
    public double average(int[] array) {
        return IntStream.of(array).average().orElse(0);
    }

    // Определение суммы элементов массива
    public int sum(int[] array) {
        return IntStream.of(array).sum();
    }

    // Подсчет количества положительных элементов
    public long countPositive(int[] array) {
        return IntStream.of(array).filter(val -> val > 0).count();
    }

    // Подсчет количества отрицательных элементов
    public long countNegative(int[] array) {
        return IntStream.of(array).filter(val -> val < 0).count();
    }
}
