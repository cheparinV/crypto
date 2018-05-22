package com.univer.algorithm.attack.generator;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class HastadGeneratorTest {

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testGenerateAll() throws Exception {
    final List<Map<String, BigInteger>> maps = new HastadGenerator().generateAll(10, 64);
    for (Map<String, BigInteger> map : maps) {
      for (String s : map.keySet()) {
        System.out.println(s + " : " + map.get(s));
      }
    }
    }
}
