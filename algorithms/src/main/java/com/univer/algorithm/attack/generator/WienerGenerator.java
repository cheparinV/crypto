package com.univer.algorithm.attack.generator;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class WienerGenerator {

  private Map<String, BigInteger> map;

  public Map<String, BigInteger> generateAll(Integer bitLength) {
    final BigInteger p = BigInteger.probablePrime(bitLength, new Random());
    final BigInteger q = BigInteger.probablePrime(bitLength, new Random());
    final BigInteger N = p.multiply(q);
    final BigInteger fN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    final Map<String, BigInteger> map = this.generateD(N, fN);
    map.put("q", q);
    map.put("p", p);
    map.put("N", N);
    map.put("fN", fN);
    this.map = map;
    return map;
  }

  public Map<String, BigInteger> generateD(BigInteger N, BigInteger fN) {
    final BigDecimal decimalN = new BigDecimal(N.toString());
    final MathContext mathContext = new MathContext(100);
    final BigDecimal sqrt = BigDecimalMath.sqrt(decimalN, mathContext);
    final BigDecimal maxD = BigDecimalMath.sqrt(
        sqrt,
        mathContext
    ).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
    boolean isReady = false;
    BigInteger e = BigInteger.ZERO;
    BigInteger d = BigInteger.ZERO;
    while (!isReady) {
      final BigInteger probableD = BigInteger
          .probablePrime(maxD.toBigInteger().bitLength(), new Random());
      e = probableD.modInverse(fN);
      final BigInteger mod = e.multiply(probableD).mod(fN);
      if (mod.compareTo(BigInteger.ONE) == 0) {
        isReady = true;
        d = probableD;
      }
    }
    final Map<String, BigInteger> result = new HashMap<>();

    final BigDecimal sqrt1 = BigDecimalMath.sqrt(sqrt, mathContext);
    final BigDecimal divide = new BigDecimal(d.toString()).divide(sqrt1, 0, BigDecimal.ROUND_HALF_UP);

    result.put("e", e);
    result.put("d", d);
    result.put("D", new BigInteger(divide.toString()));
    return result;
  }


}
