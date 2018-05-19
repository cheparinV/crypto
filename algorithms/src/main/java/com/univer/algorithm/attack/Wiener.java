package com.univer.algorithm.attack;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class Wiener {

  public BigInteger attack(BigInteger N, BigInteger e, String message) {
    final BigInteger cipherMessage = new BigInteger(message).modPow(e, N);
    final List<BigInteger> bigIntegers = this.contFraction(N, e);

    final List<Pair<BigInteger, BigInteger>> pairs = this.convergents(bigIntegers);
    for (Pair<BigInteger, BigInteger> pair : pairs) {
      BigInteger temp = new BigInteger("2");
      BigInteger secondTemp = temp.modPow(e.multiply(pair.getValue()), N);
      if (temp.equals(secondTemp)) {
        System.out.println("i = " + pairs.indexOf(pair));
        return pair.getValue();
      }
    }

    return BigInteger.ZERO;
  }


  public List<BigInteger> contFraction(BigInteger N, BigInteger d) {
    final List<BigInteger> list = new ArrayList<>();
    BigInteger q = N.divide(d);
    BigInteger r = N.mod(d);
    list.add(q);

    while (!BigInteger.ZERO.equals(r)) {
      N = d;
      d = r;
      q = N.divide(d);
      r = N.mod(d);
      list.add(q);
    }
    return list;
  }

  public List<Pair<BigInteger, BigInteger>> convergents(List<BigInteger> list) {
    final List<Pair<BigInteger, BigInteger>> pairs = new ArrayList<>();
    pairs.add(new Pair<>(BigInteger.ONE, list.get(0)));
    pairs.add(
        new Pair<>(
            list.get(1),
            list.get(0).multiply(list.get(1))
                .add(BigInteger.ONE)));

    for (int i = 2; i < list.size(); ++i) {
      pairs.add(
          new Pair<>(
              list.get(i).multiply(pairs.get(i - 1).getKey())
                  .add(pairs.get(i - 2).getKey()),
              list.get(i).multiply(pairs.get(i - 1).getValue())
                  .add(pairs.get(i - 2).getValue())));
    }
    return pairs;
  }
}
