package com.univer.algorithm.benchmark;

import com.univer.algorithm.attack.Wiener;
import com.univer.algorithm.attack.generator.WienerGenerator;
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
public class WienerBenchTest {

  public static int length = 128;


  @State(Scope.Benchmark)
  public static class ExecutionPlan {

    //@Param({"32", "48", "64", "80", "96", "112"})
    @Param({"896"})
    public int iterations;

    public static int number = 0;
    List<Map<String, BigInteger>> list;

    public WienerGenerator wienerGenerator;

    @Setup(Level.Trial)
    public void setUp() {
      wienerGenerator = new WienerGenerator();
      list = new ArrayList<>();
      for (int i = 0; i < 10; ++i) {
        final Map<String, BigInteger> map = wienerGenerator.generateAll(iterations);
        list.add(map);
        for (String s : map.keySet()) {
          System.out.println(s + " : " + map.get(s));
        }
      }
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Fork(value = 2, jvmArgs = {"-Xmx4g", "-Xms2g"})
  @Warmup(iterations = 1)
  @Measurement(iterations = 10)
  public void measureWiener(ExecutionPlan plan) {
    final Map<String, BigInteger> map = plan.list.get(ExecutionPlan.number);
    final BigInteger N = map.get("N");
    final BigInteger e = map.get("e");
    System.out.println("N : " + N);
    System.out.println("e : " + e);
    final BigInteger attack = new Wiener().attack(N, e, "1000");
    System.out.println("Success : " + map.get("d").equals(attack));
    final BigInteger bigInteger = BigInteger.valueOf(2).modPow(e, N);
    final BigInteger bigInteger1 = bigInteger.modPow(attack, N);
    System.out.println("Attack : " + attack);
    System.out.println("Cypher : " + bigInteger);
    System.out.println("Encrypt : " + bigInteger1);
    final boolean equals = BigInteger.valueOf(2).equals(bigInteger1);
    System.out.println("Success : " + equals);

    if (DujelBenchTest.ExecutionPlan.number >= 9) {
      DujelBenchTest.ExecutionPlan.number = 0;
    } else {
      DujelBenchTest.ExecutionPlan.number++;
    }
    //ExecutionPlan.number = 0;

    System.out.println(DujelBenchTest.ExecutionPlan.number);
    System.out.println("----------------------");

    //final BigInteger attack = new Wiener().attack(map.get("N"), map.get("e"), "2154215");
  }


}
