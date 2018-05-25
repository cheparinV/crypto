package com.univer.algorithm.attack.generator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class HastadGenerator {

  public List<Map<String, BigInteger>> generateAll(int count, int bitLength) {
    //final BigInteger bigInteger = BigInteger.probablePrime(count, new Random());
    final BigInteger bigInteger = BigInteger.valueOf(count);
    //final BigInteger maxE = BigInteger.probablePrime(bigInteger.bitLength(), new Random());
    final BigInteger maxE = bigInteger;
    final List<Map<String, BigInteger>> maps = new ArrayList<>();
    for (int i = 0; i < maxE.intValue(); ++i) {
      final BigInteger p = BigInteger.probablePrime(bitLength, new Random());
      final BigInteger q = BigInteger.probablePrime(bitLength, new Random());
      final BigInteger N = p.multiply(q);
      final BigInteger fN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
      Map<String, BigInteger> map;
      try {
        map = generateE(N, fN, maxE);
      } catch (Exception e) {
        e.printStackTrace();
        i--;
        continue;
      }
      map.put("q", q);
      map.put("p", p);
      map.put("N", N);
      map.put("fN", fN);
      maps.add(map);
    }
    return maps;
  }

  public Map<String, BigInteger> generateE(BigInteger N, BigInteger fN, BigInteger maxE) {
    final Map<String, BigInteger> map = new HashMap<>();
    boolean isReady = false;
    BigInteger e = BigInteger.ZERO;
    BigInteger d = BigInteger.ZERO;
    while (!isReady) {
      final BigInteger probableE = maxE;
      d = probableE.modInverse(fN);
      final BigInteger mod = d.multiply(probableE).mod(fN);
      if (mod.compareTo(BigInteger.ONE) == 0 && maxE.compareTo(probableE) >= 0) {
        isReady = true;
        e = probableE;
      }
    }
    map.put("e", e);
    map.put("d", d);
    return map;
  }

}
