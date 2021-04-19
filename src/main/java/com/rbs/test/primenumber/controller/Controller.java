package com.rbs.test.primenumber.controller;

import com.rbs.test.primenumber.service.PrimeNumberCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
        * A simple REST Controller which provide the prime number based on the upper limit of the number.
        *
        * @author Arjun Arunachalam
        */

@RestController
@RequestMapping("/primeNumber")
public class Controller {

    @Autowired
    private PrimeNumberCalculatorService primeNumberCalculatorService;

    /**
     * This API will return a list of prime number which starts from 2 to the upperlimit(including the upperlimit value)
     * @param upperLimit This is the upperLimit to which the prime number will be calculated
     * @return a list of prime number which starts from 2 to the upperlimit
     */


    @RequestMapping(method = RequestMethod.GET)
    List<Integer> primes(@RequestParam(value = "upperLimit", defaultValue = "100") int upperLimit) throws Exception{
        return primeNumberCalculatorService.getPrimeNumberTillLimit(upperLimit);
    }

}
