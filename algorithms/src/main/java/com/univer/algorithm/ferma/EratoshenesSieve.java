package com.univer.algorithm.ferma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class EratoshenesSieve {

  private Integer n;
  private List<Integer> primes;

  public EratoshenesSieve(Integer n) {
    this.n = n;
  }

  public List<Integer> getPrimes() {
    if (primes == null) {
      this.countPrimes();
    }
    return this.primes;
  }

  private void countPrimes() {
    this.primes = new ArrayList<>();
    final boolean[] booleans = new boolean[this.n + 1];
    Arrays.fill(booleans, true);
    for (int i = 2; i <= n; ++i) {
      if (booleans[i]) {
        this.primes.add(i);
        for (int j = i; j <= n; j += i) {
          booleans[j] = false;
        }
      }
    }
  }

}
