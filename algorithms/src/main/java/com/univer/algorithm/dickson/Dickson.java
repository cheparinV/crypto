package com.univer.algorithm.dickson;

import com.univer.algorithm.ferma.EratoshenesSieve;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Dickson {

  private Integer n;
  private Integer b;

  public List<Integer> chooseFBase(int n, int b) {
    final List<Integer> list = new ArrayList<>();
    final List<Integer> primes = new EratoshenesSieve(b).getPrimes();
    for (Integer prime : primes) {
      if (this.symbolLegendre(n, prime) == 1) {
        list.add(prime);
      }
    }
    return list;
  }



  public Long symbolLegendre(Integer a, Integer p) {
    final long exp = (p - 1) / 2;
    return BigInteger.valueOf(a)
        .modPow(
            BigInteger.valueOf(exp), BigInteger.valueOf(p)
        ).longValue();
  }
}
