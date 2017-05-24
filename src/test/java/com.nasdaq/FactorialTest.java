package com.nasdaq;


import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/*
CPU: Intel Core i7-4790 @ 3.60GHz
No of Cores: 4
*/
public class FactorialTest {

    private Factorial factorial;

    @Before
    public void setup() {
        factorial = new Factorial();
    }

    @Test
    public void testcase1() throws Exception {
        BigInteger result = factorial.products(5, 2);
        assertThat(result, is(BigInteger.valueOf(21)));
    }


    @Test
    public void testcase2() throws Exception {
        BigInteger result = factorial.products(100, 10);
        assertThat(result, is(new BigInteger("513946235090695726234")));
    }

    @Test
    public void testcase3() throws Exception {
        long startTime = System.currentTimeMillis();
        BigInteger result = factorial.products(1000000, 200);
        assertThat(result.toString().substring(0, 10), is("4876116127"));

        /*
        Consume 15513 milliseconds
        if we execute factorial.products( 10000000,200) , it will cost 171288 milliseconds, it verifies that time complexity is O(NC)
         */
        System.out.println(String.format("Consume %d milliseconds ", System.currentTimeMillis() - startTime));  //
    }

    @Test
    public void testcase4() throws Exception {
        long startTime = System.currentTimeMillis();
        List<Factorial> factorialList = Arrays.asList(
                new Factorial(1, 2500000, 200),
                new Factorial(2500001, 5000000, 200),
                new Factorial(5000001, 7500000, 200),
                new Factorial(7500001, 10000000, 200));

        BigInteger result = factorialList
                .parallelStream()
                .reduce(BigInteger.ZERO,
                        (sum, f) -> {
                            return sum.add(f.products());
                        },
                        (sum1, sum2) -> {
                            return sum1.add(sum2);
                        });
        System.out.println(result);

        /*
         Consume 102073 milliseconds. Much better than execute factorial.products( 10000000,200) directly which costs 171288 milliseconds
         */
        System.out.println(String.format("Consume %d milliseconds ", System.currentTimeMillis() - startTime));
    }

    @Test
    public void testcase5() throws Exception {
        long startTime = System.currentTimeMillis();
        List<Factorial> factorialList2 = Arrays.asList(
                new Factorial(1, 1000000, 200),
                new Factorial(1000001, 2000000, 200),
                new Factorial(2000001, 3000000, 200),
                new Factorial(3000001, 4000000, 200),
                new Factorial(4000001, 5000000, 200),
                new Factorial(5000001, 6000000, 200),
                new Factorial(6000001, 7000000, 200),
                new Factorial(7000001, 8000000, 200),
                new Factorial(8000001, 9000000, 200),
                new Factorial(9000001, 10000000, 200));

        BigInteger result2 = factorialList2
                .parallelStream()
                .reduce(BigInteger.ZERO,
                        (sum, f) -> {
                            return sum.add(f.products());
                        },
                        (sum1, sum2) -> {
                            return sum1.add(sum2);
                        });
        System.out.println(result2);
        /*
          Consume 110164 milliseconds, worse than testcase4 because the CPU only has 4 cores
         */
        System.out.println(String.format("Consume %d milliseconds ", System.currentTimeMillis() - startTime));
    }

}
