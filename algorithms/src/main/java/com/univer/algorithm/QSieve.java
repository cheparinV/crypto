package com.univer.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class QSieve {

  public Long SymbolLegendre(Long a, Long p) {
    final long exp = (p - 1) / 2;
    return BigInteger.valueOf(a)
        .modPow(
            BigInteger.valueOf(exp), BigInteger.valueOf(p)
        ).longValue();
  }

  public List<Integer> generateFBase(Long n, Long b) {
    final List<Integer> list = new ArrayList<>();
    final boolean[] primes = new boolean[(int) (b + 1)];
    Arrays.fill(primes, true);
    for (int i = 2; i <= b; ++i) {
      if (primes[i]) {
        list.add(i);
        for (int j = i; j <= b; j += i) {
          primes[j] = false;
        }
      }
    }
    final List<Integer> result = new ArrayList<>();
    for (Integer integer : list) {
      if (this.SymbolLegendre(n, Long.valueOf(integer)) == 1) {
        result.add(integer);
      }
    }
    return result;
  }

  public Map<Integer, Integer> sift(Integer n, Integer range) {
    final int start = (int) (Math.sqrt(n) + 1);
    final int finish = start + range;
    final Map<Integer, Integer> map = new HashMap<>();

    for (int i = start; i < finish; ++i) {
      map.put(i, i * i - n);
    }
    return map;
  }


}
