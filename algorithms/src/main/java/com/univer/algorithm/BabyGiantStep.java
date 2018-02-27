package com.univer.algorithm;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class BabyGiantStep {

    public String algo(Long n, Long alpha, Long beta) {
        String result = "";
        final double sqrt = Math.sqrt(n);
        Long m = (long) sqrt + 1;
        final Long aM = modPow(alpha, m, n);
        Long fM = modPow(alpha, m, n);
        final Map<Long, Long> map = new HashMap<>();
        for (long i = 1; i <= m; ++i) {
            if (!map.containsKey(fM)) {
                map.put(fM, i);
            }
            fM *= aM;
            fM %= n;
        }
        for (long i = 1; i <= m; ++i) {
            final Long iAlpha = this.modPow(alpha, i, n);
            final long temp = (beta * iAlpha) % n;
            if (map.containsKey(temp)) {
                result = String.valueOf(m * map.get(temp) - i);
                i += m;
            }
        }
        return result;
    }

    private Long modPow(Long a, Long b, Long n) {
        return Long.valueOf(
                BigInteger.valueOf(a).modPow(
                        BigInteger.valueOf(b), BigInteger.valueOf(n)
                ).toString()
        );
    }

    public String algo(int i, int i1, int i2) {
        return this.algo((long) i, (long) i1, (long) i2);
    }
}
