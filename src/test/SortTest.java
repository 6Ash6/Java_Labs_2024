package test.test_1;

import org.testng.Assert;

import Services.SortingService;
import Array.Array;
import org.testng.annotations.Test;

public class SortTest {
    private final SortingService sortService = new SortingService();
    private final Array unsortedArray = new Array(new int[]{5, 1, 4, 2, 10});
    private final int[] expectedSortedArray = new int[]{1, 2, 4, 5, 10};



    @Test
    public void testBubbleSort() {
        sortService.bubbleSort(unsortedArray.getElements());
        Assert.assertEquals(unsortedArray.getElements(), expectedSortedArray);
    }

    @Test
    public void testSelectionSort() {
        sortService.insertionSort(unsortedArray.getElements());
        Assert.assertEquals(unsortedArray.getElements(), expectedSortedArray);
    }

    @Test
    public void testQuickSort() {
        sortService.quickSort(unsortedArray.getElements(), 0, unsortedArray.getElements().length - 1);
        Assert.assertEquals(unsortedArray.getElements(), expectedSortedArray);
    }
}
