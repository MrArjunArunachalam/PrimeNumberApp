package com.rbs.test.primenumber.tests.service;

import com.rbs.test.primenumber.algorithm.AlgorithmImpl;
import com.rbs.test.primenumber.service.PrimeNumberCalculatorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PrimeNumberCalculatorServiceTest {

    @InjectMocks
    PrimeNumberCalculatorServiceImpl primeNumberCalculatorService;

    @Mock
    AlgorithmImpl algorithmImpl;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPrimeNumberTillLimitWithValidValues() throws Exception{
        List<Integer> responesFromAlgorithm=new ArrayList<>(Arrays.asList(2,3,5,7));
        Mockito.when(algorithmImpl.calculateThenReturnListOfPrimeNumberTill(10)).thenReturn(responesFromAlgorithm);
        final List<Integer> integers= primeNumberCalculatorService.getPrimeNumberTillLimit(10);
        Assert.assertEquals(4,integers.size());
        integers.stream().parallel().forEach(e->Assert.assertEquals(true,responesFromAlgorithm.contains(e)));
    }

    @Test
    public void testPrimeNumberTillLimitWithInvalidValues() throws Exception{
        Mockito.when(algorithmImpl.calculateThenReturnListOfPrimeNumberTill(-10)).thenReturn(new ArrayList<>());
        final List<Integer> integers= primeNumberCalculatorService.getPrimeNumberTillLimit(10);
        Assert.assertTrue(integers.isEmpty());
    }

}
