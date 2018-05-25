package com.univer.algorithm.attack.generator;

import com.univer.algorithm.attack.Hastad;
import com.univer.algorithm.attack.RSAModel;
import java.math.BigInteger;
import java.util.ArrayList;
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
    final List<Map<String, BigInteger>> maps = new HastadGenerator().generateAll(1000, 512);
    System.out.println("e : " + maps.get(0).get("e"));
    BigInteger message = new BigInteger("1024");
    final ArrayList<RSAModel> models = new ArrayList<>();
    for (Map<String, BigInteger> map : maps) {
      final BigInteger N = map.get("N");
      final BigInteger e = map.get("e");
      models.add(new RSAModel().setN(N).setE(e).setCipherMessage(message.modPow(e, N)));
    }

   new Hastad().attack(models);

  }
}
