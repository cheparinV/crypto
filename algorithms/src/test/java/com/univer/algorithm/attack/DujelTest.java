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

  @Test
  public void testAttackSecond() throws Exception {
    final BigInteger e = new BigInteger(
        "471905671341381746921017134668596017126690146166506795537661748622774701872095351587214974516179305195213887122008754059635816886663020943231049004736409");
    final BigInteger n = new BigInteger(
        "8571635563080800557977550403042692559904727182297018926704062378091411217332390001541223331848872416398570003040579886727655002076260894899326368583208577");
    //BigInteger n = new BigInteger("7978886869909");
    //BigInteger e = new BigInteger("4603830998027");
    final BigInteger attack = new Dujel().attack(n, e, 1000);
    System.out.println(attack);

    final BigInteger x = BigInteger.valueOf(2).modPow(e, n);
    System.out.println(x);
    final BigInteger bigInteger = x.modPow(attack, n);

    Assert.assertEquals("2", bigInteger.toString());
  }
}
