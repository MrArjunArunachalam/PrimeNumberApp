package com.rbs.test.primenumber.service;

import java.util.List;

public interface PrimeNumberCalculatorService {


    /**
     * This will return a list of prime number which starts from 2 to the upperlimit(including the upperlimit)
     * @param upperLimit This is the upperLimit to which the prime number will be calculated
     * @return a list of prime number which starts from 2 to the upperlimit
     */

    List<Integer> getPrimeNumberTillLimit(int upperLimit) throws Exception;
}
