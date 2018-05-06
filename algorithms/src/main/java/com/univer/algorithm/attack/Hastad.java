package com.univer.algorithm.attack;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Hastad {

    public long attack(List<RSAModel> list) {
        final BigInteger[] nArray = list.stream()
                .map(RSAModel::getN)
                .toArray(BigInteger[]::new);
        final BigInteger[] cipherArray = list.stream()
                .map(RSAModel::getCipherMessage)
                .toArray(BigInteger[]::new);

        final BigInteger message = ChineseRT.chineseRemainder(nArray, cipherArray);
        final long pow = Math.round(
                Math.pow(message.intValue(), 1 / 3));
        return pow;
    }


}
