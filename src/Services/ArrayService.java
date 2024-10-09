package Services;

import Array.Array;

public class ArrayService {

    // Поиск минимального значения в массиве
    public int findMin(Array array) {
        int[] elements = array.getElements();
        int min = elements[0];
        for (int element : elements) {
            if (element < min) {
                min = element;
            }
        }
        System.out.println("Min: " + min);
        return min;
    }

    // Поиск максимального значения в массиве
    public int findMax(Array array) {
        int[] elements = array.getElements();
        int max = elements[0];
        for (int element : elements) {
            if (element > max) {
                max = element;
            }
        }
        System.out.println("Max: " + max);
        return max;
    }

    // Замена элементов массива по условию: заменить все отрицательные элементы на 0
    public void replaceElementsByCondition(Array array) {
        int[] elements = array.getElements();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] < 0) {
                elements[i] = 0;
            }
        }
    }

    // Определение среднего значения элементов массива
    public double calculateAverage(Array array) {
        int[] elements = array.getElements();
        int sum = calculateSum(array);
        return (double) sum / elements.length;
    }

    // Определение суммы элементов массива
    public int calculateSum(Array array) {
        int[] elements = array.getElements();
        int sum = 0;
        for (int element : elements) {
            sum += element;
        }
        return sum;
    }

    // Определение числа положительных и отрицательных элементов массива
    public int[] countPositiveNegativeElements(Array array) {
        int[] elements = array.getElements();
        int positiveCount = 0;
        int negativeCount = 0;
        for (int element : elements) {
            if (element > 0) {
                positiveCount++;
            } else if (element < 0) {
                negativeCount++;
            }
        }
        return new int[]{positiveCount, negativeCount};
    }

    // Вывод массива на экран
    public void printArray(int[] array) {
        System.out.print("Array.Array elements: ");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
