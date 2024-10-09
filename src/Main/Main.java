package Main;

import Array.Array;
import IntStream.ArrayFileReader_3task;
import IntStream.ArrayService_3task;
import Services.ArrayService;
import Services.FileReaderService;
import Services.SortingService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int[] elements = {3, -5, 10, -2, 0, 7};
        Array array = new Array(elements);
        ArrayService service = new ArrayService();

        System.out.println("Before replacement:");
        service.printArray(array.getElements());

        int min = service.findMin(array);
        int max = service.findMax(array);
        int[] posNegCount = service.countPositiveNegativeElements(array);
        double average = service.calculateAverage(array);
        int sum = service.calculateSum(array);
        service.replaceElementsByCondition(array);


        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Average: " + average);
        System.out.println("Sum: " + sum);
        System.out.println("Positive count: " + posNegCount[0]);
        System.out.println("Negative count: " + posNegCount[1]);

        System.out.println("After replacement:");
        service.printArray(array.getElements());

        //2 task
        String filePath = "\\Resource\\input.txt";  // Относительный путь к файлу
        FileReaderService reader = new FileReaderService();
        SortingService sorter = new SortingService();

        try {
            // Чтение и валидация массива из файла
            int[] array_2 = reader.readArrayFromFile(filePath);
            System.out.println("Исходный массив:");
            printArray(array_2);

            // Сортировка массива тремя разными алгоритмами
            int[] bubbleSortedArray = array_2.clone();
            sorter.bubbleSort(bubbleSortedArray);
            System.out.println("После пузырьковой сортировки:");
            printArray(bubbleSortedArray);

            int[] insertionSortedArray = array_2.clone();
            sorter.insertionSort(insertionSortedArray);
            System.out.println("После сортировки выбором:");
            printArray(insertionSortedArray);

            int[] quickSortedArray = array_2.clone();
            sorter.quickSort(quickSortedArray, 0, quickSortedArray.length - 1);
            System.out.println("После быстрой сортировки:");
            printArray(quickSortedArray);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getClass());
        }


        //3 task
        ArrayService_3task arrayService = new ArrayService_3task();
        ArrayFileReader_3task reader_3task = new ArrayFileReader_3task();

        int[] array_3task = {1, -2, 3, -4, 5};

        // Пример работы с Services.ArrayService
        System.out.println("Минимум: " + arrayService.findMin(array_3task).orElse(-1));
        System.out.println("Максимум: " + arrayService.findMax(array_3task).orElse(-1));
        System.out.println("Среднее: " + arrayService.average(array_3task));
        System.out.println("Сумма: " + arrayService.sum(array_3task));
        System.out.println("Количество положительных: " + arrayService.countPositive(array_3task));
        System.out.println("Количество отрицательных: " + arrayService.countNegative(array_3task));

        // Пример сортировки массива и чтения данных из файла
        try {
            int[] arrayFromFile = reader_3task.readArrayFromFile("\\Resource\\input.txt");
            System.out.println("Исходный массив:");
            printArray(arrayFromFile);

            // Сортировка
            int[] sortedArray = reader_3task.sortArray(arrayFromFile);
            System.out.println("Отсортированный массив:");
            printArray(sortedArray);

            // Обратная сортировка
            int[] reverseSortedArray = reader_3task.reverseSortArray(arrayFromFile);
            System.out.println("Массив, отсортированный в обратном порядке:");
            printArray(reverseSortedArray);

            // Пузырьковая сортировка
            int[] bubbleSortedArray = reader_3task.bubbleSort(arrayFromFile);
            System.out.println("Массив после пузырьковой сортировки:");
            printArray(bubbleSortedArray);

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
