package com.rbs.test.primenumber.algorithm;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AlgorithmImpl implements Algorithm {

     public static final int lowerBoundForPrime=2;

    /**
     * This method will return a list of prime number which starts from 2 to the upperlimit(including the upperlimit value)
     * It uses the sieve of Erasthenes Algorithm for getting the prime numbers within the specified limit
     * @param upperLimit This is the upperLimit to which the prime number will be calculated
     * @return a list of prime number which starts from 2 to the upperlimit
     */

    public  List<Integer> calculateThenReturnListOfPrimeNumberTill(int upperLimit) throws Exception {
        if(upperLimit==Integer.MAX_VALUE){
            return new ArrayList<>();
        }
        if(upperLimit > lowerBoundForPrime) {

            try {
                boolean[] calculatedPrimeNumber = new boolean[upperLimit + 1];
                //Sieve of Eratosthenes algorithm
                IntStream.rangeClosed(0, upperLimit)
                        .parallel()
                        .forEach(i -> calculatedPrimeNumber[i] = true);


                IntStream.rangeClosed(2, Math.toIntExact(Math.round(Math.sqrt(upperLimit))))
                        .parallel()
                        .forEach(i -> setPrimeOrNot(i, calculatedPrimeNumber, upperLimit));


                return IntStream.rangeClosed(2, upperLimit)
                        .parallel()
                        .filter(e -> calculatedPrimeNumber[e])
                        .boxed()
                        .collect(Collectors.toList());

            } catch ( OutOfMemoryError ex){
                throw new Exception("Upper limit value is too big to caluclate, " +
                        "please try with smaller value Or increase the VM size");
            }
        }else if(upperLimit==lowerBoundForPrime) {
            return Arrays.asList(2);
        }else{
            return new ArrayList<>();
        }
    }

    private void setPrimeOrNot(int argValue, boolean[] calculatedPrimeNumber,int upperLimit) {
        if (calculatedPrimeNumber[argValue] == true) {
            for (int loopValue = (argValue * argValue); loopValue <= upperLimit; loopValue = loopValue + argValue) {
                calculatedPrimeNumber[loopValue] = false;
            }
        }
    }

}





