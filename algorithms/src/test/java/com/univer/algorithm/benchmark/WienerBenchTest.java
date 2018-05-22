package com.univer.algorithm.benchmark;

import com.univer.algorithm.attack.Dujel;
import com.univer.algorithm.attack.generator.WienerGenerator;
import java.math.BigInteger;
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class WienerBenchTest {

  public static int length = 128;


  @State(Scope.Benchmark)
  public static class ExecutionPlan {

    @Param({"100", "200", "300", "500", "1000"})
    public int iterations;

    public WienerGenerator wienerGenerator;

    @Setup(Level.Invocation)
    public void setUp() {
      wienerGenerator = new WienerGenerator();
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Fork(value = 1)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1)
  public void measureWiener(ExecutionPlan plan) {
    final Map<String, BigInteger> map = plan.wienerGenerator.generateAll(256);
    final BigInteger N = map.get("N");
    final BigInteger e = map.get("e");
    System.out.println("N : " + N);
    System.out.println("e : " + e);
    final BigInteger attack = new Dujel().attack(N, e, 1000);
    System.out.println("Success : " + map.get("d").equals(attack));
    final BigInteger bigInteger = BigInteger.valueOf(2).modPow(e, N);
    final BigInteger bigInteger1 = bigInteger.modPow(attack, N);
    System.out.println("Attack : " + attack);
    System.out.println("Cypher : " + bigInteger);
    System.out.println("Encrypt : " + bigInteger1);
    System.out.println("Success : " + BigInteger.valueOf(2).equals(bigInteger1));

    //final BigInteger attack = new Wiener().attack(map.get("N"), map.get("e"), "2154215");
  }

  public static void main(String[] args) {
    final Options build = new OptionsBuilder().include(WienerBenchTest.class.getSimpleName())
        .warmupIterations(1)
        .measurementIterations(1)
        .build();

    for (int i = 0; i < 5; ++i) {
      WienerBenchTest.length *= 2;
      System.out.println("Bit length : " + WienerBenchTest.length);
      try {
        new Runner(build).run();
      } catch (RunnerException e) {
        throw new IllegalStateException(e);
      }
    }
  }

}
