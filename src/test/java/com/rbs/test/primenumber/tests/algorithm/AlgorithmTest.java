package com.rbs.test.primenumber.tests.algorithm;

import com.rbs.test.primenumber.algorithm.Algorithm;
import com.rbs.test.primenumber.algorithm.AlgorithmImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgorithmImpl.class)
public class AlgorithmTest {

    @Autowired
    Algorithm algorithm;

    public static final int[] primeNumberBetween1to100 = {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
            31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97};


    @Test
    public void testPrimeNumberTillLimitWithValidValues() throws Exception{

        final List<Integer> integers= algorithm.calculateThenReturnListOfPrimeNumberTill(100);
        Assert.assertEquals(primeNumberBetween1to100.length,integers.size());
        Arrays.stream(primeNumberBetween1to100).parallel().forEach(e->Assert.assertEquals(true,integers.contains(e)));
    }

    @Test
    public void testPrimeNumberWithWrongValue() throws Exception{
        final List<Integer> integers= algorithm.calculateThenReturnListOfPrimeNumberTill(-100);
        Assert.assertTrue(integers.isEmpty());
    }

    @Test
    public void testPrimeNumberWithEdgeCase() throws Exception{
        List<Integer> integers= algorithm.calculateThenReturnListOfPrimeNumberTill(2);
        Assert.assertEquals(1,integers.size());
        integers=algorithm.calculateThenReturnListOfPrimeNumberTill(7);
        Assert.assertTrue(integers.contains(7));
        integers=algorithm.calculateThenReturnListOfPrimeNumberTill(2147483647);
        Assert.assertTrue(integers.isEmpty());

    }

    @Test(expected = Exception.class)
    public void testMemoryOutOfBoundError() throws Exception{
        List<Integer> integers= algorithm.calculateThenReturnListOfPrimeNumberTill(2147483646);
    }
}
