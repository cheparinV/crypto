package com.univer.algorithm.rabbin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class FirstRabbin {

  private Integer n;

  public FirstRabbin(Integer n) {
    this.n = n;
  }

  public Map<Integer, Integer> countWP() {
    final List<Integer> list = this.getList();
    final HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 2; i <= 100; ++i) {
      int count = 0;
      for (int j = 0; j < list.size(); ++j) {
        if (this.testMR(list.get(j), i)) {
          count++;
        }
      }
      map.put(i, count);
    }
    return map;
  }

  public List<Integer> getList() {
    final boolean[] booleans = new boolean[n + 1];
    Arrays.fill(booleans, false);
    final List<Integer> list = new ArrayList<>();
    for (int i = 2; i <= n; ++i) {
      if (!booleans[i] && i % 2 == 1) {
        for (int j = i * 3; j <= n; j += 2 * i) {
          if (!booleans[j]) {
            booleans[j] = true;
            list.add(j);
          }
        }
      }
    }
    return list;
  }

  public boolean testMR(int n, int a) {
    final int gcd = BigInteger.valueOf(n).gcd(BigInteger.valueOf(a)).intValue();
    if (a < 1 || a >= n || gcd > 1) {
      return false;
    }

    int minN = n - 1;
    int d = minN;
    int s = 0;

    while (d % 2 == 0) {
      s++;
      d /= 2;
    }

    final int tmp = BigInteger.valueOf(a).modPow(
        BigInteger.valueOf(d),
        BigInteger.valueOf(n)
    ).intValue();

    if (tmp == 1) {
      return true;
    }
    for (int i = 0; i < s; ++i) {
      final int exp = (int) Math.pow(2, i);
      final Integer newTemp = BigInteger.valueOf(tmp).modPow(
          BigInteger.valueOf(exp),
          BigInteger.valueOf(n)
      ).intValue();
      if (newTemp == minN) {
        return true;
      }
    }
    return false;
  }

}
