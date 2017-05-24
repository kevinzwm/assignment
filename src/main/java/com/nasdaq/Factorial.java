package com.nasdaq;

import java.math.BigInteger;


public class Factorial

{
    private int startPoint;
    private int endPoint;
    private int c;

    public Factorial(int startPoint, int endPoint, int c) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.c = c;
    }

    public Factorial() {
    }

    /*
      Return factorial of n
     */
    static BigInteger factorial(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else {
            BigInteger fact = BigInteger.ONE;
            for (int i = 1; i < n; i++) {
                fact = fact.multiply(BigInteger.valueOf(i));
            }
            return fact;
        }
    }

    /*
     Return the product of the *preceding* C elements.
    */
    static BigInteger factorial(int n, int c) {
        if (c < 1) {
            throw new RuntimeException("Invalid *preceding* C elements");
        } else if (c == 1) {
            return BigInteger.valueOf(n - 1);
        } else {
            BigInteger sum = BigInteger.valueOf(n - 1);
            for (int i = 2; i <= c; i++) {
                sum = sum.multiply(BigInteger.valueOf(n - i));
            }
            return sum;
        }

    }

    /*
        * this method can be used with multi-thread solution if CPU is multi-cores. for example products(10 000 000, 200)
        * can be divided into below sub tasks
        * products( 1, 1000000 ,200)
        * products( 1000001, 2000000 ,200)
        * ....
        * products( 9000001, 10000000 ,200)
        */
    BigInteger products() {
        BigInteger sum = BigInteger.ZERO;
        for (int i = this.startPoint; i <= this.endPoint; i++) {
            if (i <= c) {
                sum = sum.add(factorial(i - 1));
            } else {
                sum = sum.add(factorial(i, this.c));
            }
        }
        return sum;
    }

    BigInteger products(int n, int c) throws Exception {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= n; i++) {
            if (i <= c) {
                sum = sum.add(factorial(i - 1));
            } else {
                sum = sum.add(factorial(i, c));
            }
        }
        return sum;
    }
}