package com.rbs.test.primenumber.tests.integrationtest;


import com.rbs.test.primenumber.PrimeNumberApp;
import com.rbs.test.primenumber.tests.algorithm.AlgorithmTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PrimeNumberApp.class,
        webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int portForConnection;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetPrimeNumberForDefault(){
        ResponseEntity<int[]> primeNumbers=  restTemplate
                .getForEntity("http://localhost:" + portForConnection + "/primeNumber", int[].class);

        Assert.assertEquals(AlgorithmTest.primeNumberBetween1to100.length,primeNumbers.getBody().length);

        List<Integer> expectedList = Arrays.stream(AlgorithmTest.primeNumberBetween1to100).boxed().collect(Collectors.toList());

        Arrays.stream(primeNumbers.getBody()).parallel().forEach(e->Assert.assertTrue(expectedList.contains(e)));


    }

    @Test
    public void testGetPrimeNumberWithValidValues(){

        ResponseEntity<int[]> primeNumbers=  restTemplate
                .getForEntity("http://localhost:" + portForConnection + "/primeNumber?upperLimit=10", int[].class);
        Assert.assertEquals(4,primeNumbers.getBody().length);
        List<Integer> expectedList=new ArrayList<>(Arrays.asList(2,3,5,7));
        Arrays.stream(primeNumbers.getBody()).parallel().forEach(e->Assert.assertTrue(expectedList.contains(e)));
    }

    @Test
    public void testGetPrimeNumberWithInValidValues(){

        ResponseEntity<int[]> primeNumbers=  restTemplate
                .getForEntity("http://localhost:" + portForConnection + "/primeNumber?upperLimit=-10", int[].class);
        Assert.assertEquals(0,primeNumbers.getBody().length);

    }

    @Test
    public void testGetPrimeNumberWithEdgeCase(){
        ResponseEntity<int[]> primeNumbers=  restTemplate
                .getForEntity("http://localhost:" + portForConnection + "/primeNumber?upperLimit=2", int[].class);
        Assert.assertEquals(1,primeNumbers.getBody().length);
        int[] values= primeNumbers.getBody();
        Assert.assertEquals(2,values[0]);
        primeNumbers=  restTemplate
                .getForEntity("http://localhost:" + portForConnection + "/primeNumber?upperLimit=7", int[].class);
        values= primeNumbers.getBody();
        Assert.assertEquals(4,primeNumbers.getBody().length);
        Assert.assertEquals(7,values[3]);

    }
    @Test(expected = Exception.class)
    public void testGetPrimeNumberException(){
        ResponseEntity<int[]> primeNumbers=  restTemplate
                .getForEntity("http://localhost:" + portForConnection + "/primeNumber?upperLimit= 2147483646", int[].class);

    }




}
