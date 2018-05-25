package com.univer.algorithm.benchmark;

import com.univer.algorithm.attack.Hastad;
import com.univer.algorithm.attack.RSAModel;
import com.univer.algorithm.attack.generator.HastadGenerator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class HastadBenchTest {

  @State(Scope.Benchmark)
  public static class ExecutionPlan {

    @Param({"100", "200", "300", "500", "1000"})
    public int iterations;

    public HastadGenerator hastadGenerator;

    @Setup(Level.Invocation)
    public void setUp() {
      hastadGenerator = new HastadGenerator();
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Fork(value = 1)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1)
  public void measureWiener(ExecutionPlan plan) {
    final List<Map<String, BigInteger>> maps = plan.hastadGenerator.generateAll(401, 256);

    BigInteger message = new BigInteger("1024");
    final ArrayList<RSAModel> models = new ArrayList<>();
    System.out.println("e : " + maps.get(0).get("e"));
    for (Map<String, BigInteger> map : maps) {
      final BigInteger N = map.get("N");
      final BigInteger e = map.get("e");
      models.add(new RSAModel().setN(N).setE(e).setCipherMessage(message.modPow(e, N)));
    }

    final BigDecimal attack = new Hastad().attack(models);

    System.out.println("Succes : " + attack.toString().contains("1024"));
  }

}
