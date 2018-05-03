package com.univer.algorithm.rabbin;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class FirstRabbinTest {

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testCountWP() throws Exception {

    final boolean b = new FirstRabbin(1).testMR(29, 2);

    final Map<Integer, Integer> map = new FirstRabbin((int) Math.pow(10, 6))
        .countWP();
    map.entrySet().stream()
        .sorted(Comparator.comparingInt(Entry::getValue))
        .forEach(obj -> {
          System.out.printf("%d : %d \n", obj.getKey(), obj.getValue());
        });

//    for (Integer integer : map.keySet()) {
//      System.out.printf("%d : %d \n", integer, map.get(integer));
//    }
  }

  @Test
  public void testSecond() throws Exception {

    final int[] ints = {15, 2, 94, 11, 29, 10, 31, 73};

    final Map<Integer, Integer> map = new SecondRabin().cryptoMethod(10000000, ints);

    for (Integer integer : map.keySet()) {
      System.out.println(integer + " : " + map.get(integer));
    }
  }
}
