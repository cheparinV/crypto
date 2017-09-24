package algorithm;

import java.math.BigInteger;

/**
 * Created by ASUS-PC on 25.09.2017.
 */
public class Pollard {

    public BigInteger pollard(BigInteger N) {
        BigInteger x = BigInteger.valueOf(2l);
        BigInteger y = x.pow(2)
                .subtract(BigInteger.ONE)
                .mod(N);
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
            y = y.pow(2)
                    .subtract(BigInteger.ONE)
                    .mod(N);
            y = y.pow(2)
                    .subtract(BigInteger.ONE)
                    .mod(N);
            x = x.pow(2)
                    .subtract(BigInteger.ONE)
                    .mod(N);
            System.out.println(x + " " + y);
            i++;
        }
        return new BigInteger(
                euclid.getNOD()
        );
    }

    public static void main(String[] args) {
        BigInteger pollard = new Pollard()
                .pollard(new BigInteger(
                        "1387"
                ));
        System.out.println(pollard);
    }
}
