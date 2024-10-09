package test.test_1;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import Services.ArrayService;
import Array.Array;
import org.testng.annotations.Test;

public class ServicesTest {

    private ArrayService arrayService;
    private Array array;

    @BeforeMethod
    public void setUp() {
        arrayService = new ArrayService();
        array = new Array(new int[]{3, -5, 10, -2, 0, 7});
    }

    @Test
    public void findMin() {
        int min = arrayService.findMin(array);
        Assert.assertEquals(min, -5);
    }

    @Test
    public void findMax() {
        int min = arrayService.findMax(array);
        Assert.assertEquals(min, -10);
    }

    @Test
    public void replaceElementsByCondition() {
        arrayService.replaceElementsByCondition(array);
        Assert.assertEquals(array.getElements(), new int[]{3, 0, 10, 0, 0, 7});
    }

    @Test
    public void calculateAverage() {
        double average = arrayService.calculateAverage(array);
        Assert.assertEquals(average, 2.1);
    }

    @Test
    public void calculateSum() {
        int sum = arrayService.calculateSum(array);
        Assert.assertEquals(sum, 13);
    }

    @Test
    public void countPositiveNegativeElements() {
        int[] count = arrayService.countPositiveNegativeElements(array);
        Assert.assertEquals(count[0], 3);
        Assert.assertEquals(count[0], 2);
    }
}
