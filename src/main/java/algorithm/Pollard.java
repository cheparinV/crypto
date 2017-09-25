package algorithm;

import java.math.BigInteger;

/**
 * Created by ASUS-PC on 25.09.2017.
 */
public class Pollard {

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
                System.out.println(
                        "x = " + x + "  " +
                                " y = " + y +
                                " nod = " + nod +
                                " i = " + i
                );
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
        BigInteger pollard = new Pollard()
                .pollard(new BigInteger(
                       "344572667627327576872986520507"
                      //  "1387"
                ));
        System.out.println(pollard);
    }
}
