package com.univer.algorithm;

import java.math.BigInteger;

import java.util.*;

/**
 * Created by ASUS-PC on 24.05.2017.
 */
public class PrimeGenerator {

    public boolean isPrimeMRTest(BigInteger prime) {
        BigInteger two = BigInteger.valueOf(2l);
        if (prime.mod(two).equals(BigInteger.ZERO)) {
            return false;
        }
        if (prime.compareTo(BigInteger.valueOf(3l)) <= 0) {
            return true;
        }
        BigInteger mPrime = prime.subtract(BigInteger.ONE);
        BigInteger t = prime.subtract(BigInteger.ONE);
        int s = 0;
        while (t.mod(two).equals(BigInteger.ZERO)) {
            t = t.divide(two);
            s++;
        }
        long count = prime.bitLength()/10;
        Set<BigInteger> aSet = new HashSet<BigInteger>();
        while (count > 0) {
            BigInteger a = BigInteger.ZERO;
            do {
                a = new BigInteger(mPrime.bitLength(), new Random());
            }
            while (prime.compareTo(a) <= 0 || a.equals(BigInteger.ONE) || aSet.contains(a));
            aSet.add(a);
            if (prime.mod(a).equals(BigInteger.ZERO)) {
                return false;
            }
            BigInteger b = new PowMod().powMod(a, t, prime);
            if (b.equals(BigInteger.ONE)) {
                continue;
            }

            for (int i = 0; i < s - 1 && !b.equals(mPrime); i++) {
                b = new PowMod().powMod(b, two, prime);
            }
            if (!b.equals(mPrime)) {
                return false;
            }
            count--;
        }
        return true;
    }

    public BigInteger primeGenerationBySize(int size) {
        String s;
        List<BigInteger> list = new ArrayList<>();
        while (true) {
            s = new String();
            for (int i = 0; i < size; i++) {
                s += new Random().nextInt(10);
            }
            BigInteger prime = new BigInteger(s);
        //    System.out.println(prime);
            if (isPrimeMRTest(prime)) {
                return prime;
            }
        }
    }

    public BigInteger primeGenerate() {
        int limit = 1000;
        int sqrtLimit;
        boolean[] isPrime = new boolean[limit + 1];
        int x2, y2;
        int n;

        sqrtLimit = (int) Math.sqrt(limit);
        for (int i = 0; i <= limit; i++) {
            isPrime[i] = false;
        }
        isPrime[2] = true;
        isPrime[3] = true;

        x2 = 0;
        for (int i = 1; i <= sqrtLimit; i++) {
            x2 += 2 * i - 1;
            y2 = 0;
            for (int j = 1; j <= sqrtLimit; j++) {
                y2 += 2 * j - 1;

                n = 4 * x2 + y2;
                if ((n <= limit) && (n % 12 == 1 || n % 12 == 5)) {
                    isPrime[n] = !isPrime[n];
                }

                n -= x2;
                if ((n <= limit) && (n % 12 == 7)) {
                    isPrime[n] = !isPrime[n];
                }

                n -= 2 * y2;
                if ((i > j) && (n <= limit) && (n % 12 == 11)) {
                    isPrime[n] = !isPrime[n];
                }
            }
        }

        for (int i = 5; i <= sqrtLimit; i++) {
            if (isPrime[i]) {
                n = i * i;
                for (int j = n; j <= limit; j += n) {
                    isPrime[j] = false;
                }
            }
        }

        System.out.println("2 3 5");
        for (int i = 6; i <= limit; i++) {
            if (isPrime[i]) {
                System.out.print(" " + i);
            }
        }
        return BigInteger.ONE;
    }

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        long start = System.currentTimeMillis();
        System.out.println(primeGenerator.primeGenerationBySize(309));
        BigInteger bigInteger = BigInteger.probablePrime(1024, new Random());
        System.out.println(primeGenerator.isPrimeMRTest(bigInteger));
        //System.out.println(bigInteger);
        //System.out.println(bigInteger.toString().length());
        long end = System.currentTimeMillis();
        System.out.println("Time");
        System.out.println((end - start) / 1000);
        // System.out.println(primeGenerator.isPrimeMRTest(BigInteger.valueOf(997l)));
        //primeGenerator.primeGenerate();
    }
}
