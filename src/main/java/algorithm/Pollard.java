package algorithm;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
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

    public String decrypt(String N, String e, String SW) {
        final BigInteger nInt = new BigInteger(N);
        final BigInteger divisorP = this.divisor(nInt);
        final BigInteger divisorQ = nInt.divide(divisorP);
        final BigInteger fN = divisorP.subtract(BigInteger.ONE)
                .multiply(
                        divisorQ.subtract(BigInteger.ONE)
                );
        final Euclid euclid = new Euclid().countEuclid(fN, new BigInteger(e));
        final BigInteger d = new BigInteger(
                euclid.getY()
        );
        final BigInteger bigInteger = new PowMod().powMod(new BigInteger(SW), d, nInt);
        return decryptText(bigInteger.toString());
    }

    private String decryptText(String numberText) {
        final char[] chars = numberText.toCharArray();
        final StringBuilder tmp = new StringBuilder();
        final StringBuilder builder = new StringBuilder();
        final Map<Integer, Character> map = getMap();
        for (int i = 0; i < chars.length; ++i) {
            tmp.append(chars[i]);
            if (i % 2 == 1) {
                builder.append(
                        map.get(
                                Integer.parseInt(tmp.toString())
                        )
                );
                tmp.setLength(0);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        System.out.println(
                new Pollard().decrypt(
                        "2945551046056896373232621994521",
                        "1578240369330316947030869051291",
                        "2268046305917896117885320940385"
                )
        );
//        final long start1 = System.currentTimeMillis();
//
//        final BigInteger N = new BigInteger("5087308846537040504749735052677");
//        final BigInteger e = new BigInteger("635964111226216674951775514511");
//        final BigInteger SW = new BigInteger("3009767917863379873938622968631");
//        final BigInteger divisorP = new Pollard().divisor(N);
//        final BigInteger divisorQ = N.divide(divisorP);
//        final BigInteger fN = divisorP.subtract(BigInteger.ONE)
//                .multiply(
//                        divisorQ.subtract(BigInteger.ONE)
//                );
//        final Euclid euclid = new Euclid().countEuclid(fN, e);
//        final BigInteger d = new BigInteger(
//                euclid.getY()
//        );
//        final BigInteger bigInteger = new PowMod().powMod(SW, d, N);
//        System.out.println(bigInteger);
//
//        final long end1 = System.currentTimeMillis();
//        System.out.println((end1 - start1) / 1000);
    }

    private static Map<Integer, Character> getMap() {
        final Map<Integer, Character> map = new HashMap<>();
        for (int i = 16, j = 1072; i < 79; ++i, j++) {
            map.put(i, ((char) j));
            if (((char) j) == 'я') {
                j = 1039;
            }
        }
        return map;
    }

}
