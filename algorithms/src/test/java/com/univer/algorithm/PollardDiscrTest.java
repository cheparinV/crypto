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

    final PollardDiscr pollardDiscr0 = new PollardDiscr(3L, 15L, 43L);
    System.out.println(pollardDiscr0.premutiveX());
    final Long x0 = pollardDiscr0.getX();
    System.out.println(x0);

    final PollardDiscr pollardDiscr = new PollardDiscr(3L, 13L, 23L);
    System.out.println(pollardDiscr.premutiveX());
    final Long x = pollardDiscr.getX();
    System.out.println(x);

    final PollardDiscr pollardDiscr1 = new PollardDiscr(5L, 3L, 23L);
    System.out.println(pollardDiscr1.premutiveX());
    final Long x1 = pollardDiscr1.getX();
    System.out.println(x1);

    final PollardDiscr pollardDiscr2 = new PollardDiscr(2L, 5L, 29L);
    System.out.println(pollardDiscr2.premutiveX());
    final Long x2 = pollardDiscr2.getX();
    System.out.println(x2);

    final PollardDiscr pollardDiscr3 = new PollardDiscr(2L, 8L, 19L);
    System.out.println(pollardDiscr3.premutiveX());
    final Long x3 = pollardDiscr3.getX();
    System.out.println(x3);



  }
}
