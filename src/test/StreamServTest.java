package test.test_1;

import Services.ArrayService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import IntStream.ArrayService_3task;
import Array.Array;

import org.testng.annotations.Test;

public class StreamServTest {
    private Array array;
    private ArrayService_3task arrayService;

    @BeforeMethod
    public void setUp() {
        array = new Array(new int[]{3, -5, 10, -2, 0, 7});
    }

    @Test
    public void findMin() {
        int min = arrayService.findMin(array.getElements()).orElse(-1);
        Assert.assertEquals(min, -5);
    }

    @Test
    public void findMax() {
        int min = arrayService.findMax(array.getElements()).orElse(-1);
        Assert.assertEquals(min, -10);
    }

    @Test
    public void calculateAverage() {
        double average = arrayService.average(array.getElements());
        Assert.assertEquals(average, 2.1);
    }

    @Test
    public void calculateSum() {
        int sum = arrayService.sum(array.getElements());
        Assert.assertEquals(sum, 13);
    }

    @Test
    public void countPositiveElements() {
        long pos = arrayService.countPositive(array.getElements());
        Assert.assertEquals(pos, 3);
    }

    @Test
    public void countNegativeElements() {
        long pos = arrayService.countNegative(array.getElements());
        Assert.assertEquals(pos, 2);
    }

}
