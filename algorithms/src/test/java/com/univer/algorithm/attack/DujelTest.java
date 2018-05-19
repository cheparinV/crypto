package com.univer.algorithm.attack;

import java.math.BigInteger;
import org.junit.Assert;
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
    final BigInteger attack = new Dujel().attack(n, e, 1000);
    System.out.println(attack);

    final BigInteger x = BigInteger.valueOf(2).modPow(
        e.multiply(attack), n
    );
    System.out.println(x);

    Assert.assertEquals("2", x.toString());
  }
}
