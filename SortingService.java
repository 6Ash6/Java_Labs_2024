import java.util.Arrays;

public class SortingService {

    // Пузырьковая сортировка
    public void bubbleSort(Array array) {
        int[] elements = array.getElements();
        int n = elements.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    // Сортировка выбором
    public void selectionSort(Array array) {
        int[] elements = array.getElements();
        int n = elements.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (elements[j] < elements[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = elements[minIndex];
            elements[minIndex] = elements[i];
            elements[i] = temp;
        }
    }

    // Быстрая сортировка
    public void quickSort(int[] elements, int low, int high) {
        if (low < high) {
            int pi = partition(elements, low, high);
            quickSort(elements, low, pi - 1);
            quickSort(elements, pi + 1, high);
        }
    }

    private int partition(int[] elements, int low, int high) {
        int pivot = elements[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (elements[j] < pivot) {
                i++;
                int temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }
        int temp = elements[i + 1];
        elements[i + 1] = elements[high];
        elements[high] = temp;
        return i + 1;
    }
}
