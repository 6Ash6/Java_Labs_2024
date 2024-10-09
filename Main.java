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
        String filePath = "D:\\Java\\Lab1\\src\\input.txt";  // Относительный путь к файлу
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
    }
        private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

}
