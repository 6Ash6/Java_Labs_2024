
public class Main {
    public static void main(String[] args) {
        int[] elements = {3, -5, 10, -2, 0, 7};
        Array array = new Array(elements);
        ArrayService service = new ArrayService();

        System.out.println("Before replacement:");
        service.printArray(array);


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
        service.printArray(array);
    }
}
