package com.univer.algorithm.rabbin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class SecondRabin {

  public Map<Integer, Integer> cryptoMethod(Integer n, int[] integers) {

    final FirstRabbin firstRabbin = new FirstRabbin(n);
    final HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 1; i <= integers.length; ++i) {
      map.put(i, 0);
    }
    final List<Integer> list = firstRabbin.getList();
    for (int i = 2; i < list.size(); ++i) {
      int key = 0;
      boolean testMR = true;
      for (int j = 0; j < integers.length && testMR; ++j) {
        testMR = firstRabbin.testMR(list.get(i), integers[j]);
        if (testMR) {
          key++;
        }
      }
      if (key != 0) {
        map.put(key, map.get(key) + 1);
      }
    }

//    for (Integer integer : list) {
//      boolean isGood = true;
//      int i = 0;
//      for (i = 0; i < n; ++i) {
//        isGood = true;
//        if (!firstRabbin.testMR(integer, integers[i])) {
//          isGood = false;
//        }
//      }
//      if (i > 0 && i < 11) {
//        map.put(integers[i], map.get(integers[i]) + 1);
//      }
//    }

    return map;
  }

}
