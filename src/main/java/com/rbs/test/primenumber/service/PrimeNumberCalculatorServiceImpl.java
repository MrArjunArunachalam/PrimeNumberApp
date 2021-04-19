package com.rbs.test.primenumber.service;

import com.rbs.test.primenumber.algorithm.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimeNumberCalculatorServiceImpl implements PrimeNumberCalculatorService {

    @Autowired
    Algorithm algorithm;

    @Override
    public List<Integer> getPrimeNumberTillLimit(int upperLimit) throws Exception {
        return algorithm.calculateThenReturnListOfPrimeNumberTill(upperLimit);
    }
}
