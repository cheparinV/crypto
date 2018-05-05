package com.univer.algorithm.attack;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class Wiener {

    public BigInteger attack(BigInteger N, BigInteger e, String message) {
        final BigInteger cipherMessage = new BigInteger(message).modPow(e, N);
        final List<BigInteger> bigIntegers = this.contFraction(N, e);
        final List<List<BigInteger>> convergents = this.convergents(bigIntegers);

        for (int i = convergents.get(0).size() - 1; i >= 0; --i) {
            final BigInteger mod = e.multiply(convergents.get(1).get(i)).subtract(BigInteger.ONE)
                    .mod(convergents.get(0).get(i));
            if (mod.equals(BigInteger.ZERO)) {
                return convergents.get(1).get(i);
            }
            BigInteger temp = cipherMessage.modPow(convergents.get(1).get(i), N);
            if (temp.equals(cipherMessage)) {
                return convergents.get(0).get(i);
            }
        }
        return BigInteger.ZERO;
    }


    public List<BigInteger> contFraction(BigInteger N, BigInteger d) {
        final List<BigInteger> list = new ArrayList<>();
        BigInteger q = N.divide(d);
        BigInteger r = N.mod(d);
        list.add(q);

        while (!BigInteger.ZERO.equals(r)) {
            N = d;
            d = r;
            q = N.divide(d);
            r = N.mod(d);
            list.add(q);
        }
        return list;
    }

    public List<List<BigInteger>> convergents(List<BigInteger> list) {
        final List<BigInteger> nominators = new ArrayList<>();
        final List<BigInteger> denominators = new ArrayList<>();

        //init i = 1 and i = 2
        nominators.add(list.get(0));
        denominators.add(BigInteger.ONE);
        nominators.add(
                list.get(0).multiply(list.get(1))
                        .add(BigInteger.ONE)
        );
        denominators.add(list.get(1));

        for (int i = 2; i < list.size(); ++i) {
            nominators.add(
                    list.get(i).multiply(nominators.get(i - 1))
                            .add(nominators.get(i - 2))
            );
            denominators.add(
                    list.get(i).multiply(denominators.get(i - 1))
                            .add(denominators.get(i - 2))
            );
        }

        final List<List<BigInteger>> lists = new ArrayList<>();
        lists.add(nominators);
        lists.add(denominators);
        return lists;
    }
}
