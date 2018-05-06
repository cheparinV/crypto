package com.univer.algorithm.attack;


import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class ChineseRT {

    public static BigInteger chineseRemainder(BigInteger[] n, BigInteger[] a) {

        BigInteger prod = Arrays.stream(n).reduce(BigInteger.ONE, BigInteger::multiply);

        BigInteger p = BigInteger.ZERO;
        BigInteger sm = BigInteger.ZERO;
        for (int i = 0; i < n.length; i++) {
            p = prod.divide(n[i]);
            sm = sm.add(
                    a[i].multiply(mulInv(p, n[i]))
                            .multiply(p));
        }
        return sm.mod(prod);
    }


    private static BigInteger mulInv(BigInteger a, BigInteger b) {
        BigInteger b0 = b;
        BigInteger x0 = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ONE;

        if (b.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }

        while (a.compareTo(BigInteger.ONE) == 1) {
            BigInteger q = a.divide(b);
            BigInteger amb = a.mod(b);
            a = b;
            b = amb;
            BigInteger xqx = x1.subtract(q.multiply(x0));
            x1 = x0;
            x0 = xqx;
        }

        if (x1.compareTo(BigInteger.ZERO) == -1) {
            x1 = x1.add(b0);
        }

        return x1;
    }

    public static void main(String[] args) {
        BigInteger[] n = {BigInteger.valueOf(3),
                BigInteger.valueOf(5),
                BigInteger.valueOf(7)};
        BigInteger[] a = {BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(2)};
        System.out.println(chineseRemainder(n, a));
    }
}
