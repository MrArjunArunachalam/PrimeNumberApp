package com.rbs.test.primenumber.algorithm;

import java.util.List;

public interface Algorithm {

    /**
     * This will return a list of prime number which starts from 2 to the upperlimit
     * @param upperLimit This is the upperLimit to which the prime number will be calculated(including the upperlimit)
     * @return a list of prime number which starts from 2 to the upperlimit
     */

    List<Integer> calculateThenReturnListOfPrimeNumberTill(int upperLimit) throws Exception;
}
