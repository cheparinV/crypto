package algorithm;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by ASUS-PC on 25.09.2017.
 */
public class Pollard {

    public static BigInteger f(BigInteger x, BigInteger c, BigInteger N) {
        return x.multiply(x).mod(N).add(c);
    }

    public BigInteger divisor(BigInteger N) {
        final BigInteger TWO = BigInteger.valueOf(2);
        BigInteger c = new BigInteger(N.bitLength(), new Random());
        BigInteger x = new BigInteger(N.bitLength(), new Random());
        BigInteger y = new BigInteger(x.toString());
        BigInteger divisor = BigInteger.ONE;

        if (N.mod(TWO).compareTo(BigInteger.ZERO) == 0) {
            return TWO;
        }

        while (divisor.compareTo(BigInteger.ONE) == 0) {
            x = f(x, c, N);
            y = f(f(y, c, N), c, N);
            divisor = x.subtract(y).gcd(N);
        }
        return divisor;

    }

    public BigInteger pollard(BigInteger N) {
//        BigInteger x = new BigInteger(N.bitLength(), new Random());
        BigInteger x = BigInteger.valueOf(2l);
        BigInteger y = BigInteger.ONE;
        int i = 0;
        int stage = 2;
        Euclid euclid = new Euclid();
        euclid.countEuclid(
                N, x.subtract(y).abs()
        );
        BigInteger nod = new BigInteger(
                euclid.countEuclid(
                        N, x.subtract(y).abs()
                ).getNOD());
        while (nod.equals(BigInteger.ONE)) {

            if (i == stage) {
                y = x;
                stage *= 2;
//                System.out.println(
//                        "x = " + x + "  " +
//                                " y = " + y +
//                                " nod = " + nod +
//                                " i = " + i
//                );
            }
            x = x.pow(2)
                    .subtract(BigInteger.ONE)
                    .mod(N);

            nod = new BigInteger(
                    euclid.countEuclid(
                            N, x.subtract(y).abs()
                    ).getNOD()
            );
            i++;
        }

        return nod;
    }

    public static void main(String[] args) {
////        BigInteger pollard = new Pollard()
////                .pollard(new BigInteger(
////                       "344572667627327576872986520507"
////                      //  "1387"
////                ));
//        System.out.println(pollard);

//        final PrimeGenerator generator = new PrimeGenerator();
//        final BigInteger first = generator.primeGenerationBySize(12);
//        final BigInteger second = generator.primeGenerationBySize(12);
//        System.out.println(first + " " + second);

        final long start1 = System.currentTimeMillis();
        final BigInteger N = new BigInteger("5087308846537040504749735052677");
        final BigInteger e = new BigInteger("635964111226216674951775514511");
        final BigInteger SW = new BigInteger("3009767917863379873938622968631");
        final BigInteger divisorP = new Pollard().divisor(N);
        final BigInteger divisorQ = N.divide(divisorP);
        final BigInteger fN = divisorP.subtract(BigInteger.ONE)
                .multiply(
                        divisorQ.subtract(BigInteger.ONE)
                );
        final Euclid euclid = new Euclid().countEuclid(fN, e);
        final BigInteger d = new BigInteger(
                euclid.getY()
        );
        final BigInteger bigInteger = new PowMod().powMod(SW, d, N);
        System.out.println(bigInteger);

        final long end1 = System.currentTimeMillis();
        System.out.println((end1 - start1) / 1000);



//        final long start = System.currentTimeMillis();
//        System.out.println(new Pollard().pollard(first.multiply(second)));
//        final long end = System.currentTimeMillis();
//        System.out.println((end - start) / 1000);

    }
}
