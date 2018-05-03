package com.univer.algorithm.ferma;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class FermaTest {

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testIsComposite() throws Exception {
    final int expected = 89755;
    final Integer composite = new Ferma(expected, 100).isComposite();
    final int var = expected / composite;
    Assert.assertEquals(expected, composite * var);
    System.out.printf("Expected : %d, var1: %d, var2: %d  \n", expected, composite, var);
  }
}
