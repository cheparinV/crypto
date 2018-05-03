package com.univer.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.util.Pair;

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
      map.put(i, Math.floorMod(i * i, n));
    }
    return map;
  }

  public Integer gcdForQSieve(List<Pair<Integer, Integer>> pairs, List<List<Integer>> vector,
      Integer n) {
    for (List<Integer> integers : vector) {
      Integer x = 1;
      Integer sqrt = 1;
      for (int i = 0; i < integers.size(); ++i) {
        if (integers.get(i) == 1) {
          x *= pairs.get(i).getFirst();
          sqrt *= pairs.get(i).getSecond();
        }
      }
      sqrt = (int) Math.sqrt(sqrt);
      final int dif = x - sqrt;
      final int gcd = BigInteger.valueOf(dif).gcd(BigInteger.valueOf(n)).intValue();
      if (gcd != 1 && gcd != n) {
        return gcd;
      }
    }
    return 1;
  }

}
