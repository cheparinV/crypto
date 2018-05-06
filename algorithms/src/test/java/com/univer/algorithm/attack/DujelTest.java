package com.univer.algorithm.attack;

import java.math.BigInteger;
import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class DujelTest {

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testAttack() throws Exception {
    final BigInteger e = new BigInteger("4603830998027");
    final BigInteger n = new BigInteger("7978886869909");
    final BigInteger attack = new Dujel().attack(n, e, 10000);
    System.out.println(attack);

    System.out.println(BigInteger.valueOf(2).modPow(
        e.multiply(attack),
        n
    ));
  }
}
