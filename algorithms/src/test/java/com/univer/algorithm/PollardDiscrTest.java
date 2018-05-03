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

//    final PollardDiscr pollardDiscr0 = new PollardDiscr(2L, 5L, 29L);
//    System.out.println(pollardDiscr0.premutiveX());
//    final Long x0 = pollardDiscr0.getX();
//    System.out.println(x0);

    final PollardDiscr pollardDiscr01 = new PollardDiscr(10L, 64L, 107L);
    //System.out.println(pollardDiscr01.premutiveX());
    final Long x01 = pollardDiscr01.getX();
    System.out.println(x01);



  }
}
