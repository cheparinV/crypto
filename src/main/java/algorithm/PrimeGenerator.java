package algorithm;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by ASUS-PC on 24.05.2017.
 */
public class PrimeGenerator {

    public boolean isPrime(BigInteger prime) {
        BigInteger two = BigInteger.valueOf(2l);
        if (prime.mod(two).equals(BigInteger.ZERO)) {
            return false;
        }
        if (prime.compareTo(BigInteger.valueOf(3l)) <= 0) {
            return true;
        }
        BigInteger mPrime = prime.subtract(BigInteger.ONE);
        //System.out.println(mPrime);
        BigInteger d = prime.subtract(BigInteger.ONE);
        int s = 0;
        while (d.mod(two).equals(BigInteger.ZERO)) {
            d = d.divide(two);
            s++;
        }
        BigInteger a = two;
        BigInteger r = BigInteger.valueOf((long) Math.log(prime.doubleValue()) + 1);
        BigInteger x;
        while (!a.equals(r)) {
            int intD = d.intValue();
            if (intD < 0) {
                return false;
            }
            x = a.pow(intD).mod(prime);
            boolean isPrime = (x.equals(BigInteger.ONE) ||
                    x.equals(mPrime));
            int i = 1;
            while ((!isPrime) && (i <= s-1)) {
                x = x.pow(2).mod(prime);
                //System.out.println(i);
                isPrime = (x.equals(BigInteger.ONE) ||
                        x.equals(mPrime));
                i++;
            }
            if (!isPrime) {
                //System.out.println(isPrime);
                return false;
            }
            a = a.add(BigInteger.ONE);
        }

        return true;
    }

    public BigInteger primeGenerationBySize(int size) {
        String s;
        while (true) {
            s = new String();
            for (int i = 0; i < size; i++) {
                s += new Random().nextInt(10);
            }
            BigInteger prime = new BigInteger(s);
            System.out.println(prime);
            if (isPrime(prime)) {
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
        System.out.println(primeGenerator.primeGenerationBySize(10));
        System.out.println(primeGenerator.isPrime(BigInteger.valueOf(997l)));
        primeGenerator.primeGenerate();
    }
}
