package com.univer.algorithm;

import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class PollardDiscrTest {

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testGetX() throws Exception {

    final PollardDiscr pollardDiscr = new PollardDiscr(3L,  13L, 25L);
    System.out.println(pollardDiscr.premutiveX());
    System.out.println(pollardDiscr.getX());
    final PollardDiscr discr = new PollardDiscr(10L, 64L, 107L);
    System.out.println(discr.premutiveX());
    System.out.println(discr.getX());
  }
}
