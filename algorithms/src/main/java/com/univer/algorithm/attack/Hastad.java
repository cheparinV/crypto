package com.univer.algorithm.attack;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
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
        final MathContext mathContext = new MathContext(100);
        final BigDecimal root = BigDecimalMath
            .root(new BigDecimal(message.toString()), BigDecimal.valueOf(3), mathContext);
        System.out.println(root);
        return 10L;
    }


}
