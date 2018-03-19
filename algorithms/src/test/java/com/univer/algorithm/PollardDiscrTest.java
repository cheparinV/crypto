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

    final PollardDiscr pollardDiscr = new PollardDiscr(3L, 13L, 23L);
    System.out.println(pollardDiscr.premutiveX());
    final Long x = pollardDiscr.getX();
    System.out.println(x);


  }
}
