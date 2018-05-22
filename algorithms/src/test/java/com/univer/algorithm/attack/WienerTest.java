package com.univer.algorithm.attack;

import java.math.BigInteger;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 * @version $Id$
 * @since 1.0
 */
public class WienerTest {

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testContFraction() throws Exception {
    new Wiener().contFraction(BigInteger.valueOf(22L), BigInteger.valueOf(7L))
        .forEach(
            System.out::println
        );
  }

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testConvergents() throws Exception {
    final Wiener wiener = new Wiener();
    final List<BigInteger> bigIntegers = wiener.contFraction(
        BigInteger.valueOf(22L), BigInteger.valueOf(7L)
    );
    wiener.convergents(bigIntegers).forEach(
        System.out::println
    );
  }

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testAttack() throws Exception {
    final String message = "168";
    final BigInteger e = new BigInteger("17993");
    final BigInteger n = new BigInteger("90581");
    final BigInteger attack = new Wiener().attack(n, e, message);
    System.out.println(attack);
    final BigInteger bigInteger = new BigInteger(message).modPow(e, n);
    final BigInteger bigInteger1 = bigInteger.modPow(attack, n);
    System.out.println(bigInteger1);
    Assert.assertEquals(message, bigInteger1.toString());
  }

  @Test
  public void testAttackSecond() throws Exception {
    final String message = "168";
    final BigInteger e = new BigInteger("471905671341381746921017134668596017126690146166506795537661748622774701872095351587214974516179305195213887122008754059635816886663020943231049004736409");
    final BigInteger n = new BigInteger("8571635563080800557977550403042692559904727182297018926704062378091411217332390001541223331848872416398570003040579886727655002076260894899326368583208577");
    final BigInteger attack = new Wiener().attack(n, e, message);
    System.out.println(attack);
    final BigInteger bigInteger = new BigInteger(message).modPow(e, n);
    final BigInteger bigInteger1 = bigInteger.modPow(attack, n);
    System.out.println(bigInteger1);
    Assert.assertEquals(message, bigInteger1.toString());
  }
}
