package com.univer.algorithm.benchmark;

import com.univer.algorithm.attack.Dujel;
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
public class DujelBenchTest {

  @State(Scope.Benchmark)
  public static class ExecutionPlan {

    @Param({"64", "128", "256", "512", "768", "1024"})
    //@Param({"1280", "1536", "1792", "2048"})
    public int iterations;

//    @Param({"32"})
//    public int coef;

    //@Param({"40", "44", "50"})
//    @Param({"32", "36"})
//    public int dIterations;// "10", "100", "1000",

    //@Param({"1000000", "10000000"})
    //@Param({"10", "100", "1000", "10000"})
    @Param({"1000000"})
    public int range;

    public static int errors = 0;
    public static int all = 0;

    public static int number = 0;
    List<Map<String, BigInteger>> list;

    public WienerGenerator wienerGenerator;

    @Setup(Level.Trial)
    public void setUp() {
      wienerGenerator = new WienerGenerator();
      list = new ArrayList<>();
      all = 0;
      errors = 0;
//      iterations *= coef;
//      dIterations *= coef;
      for (int i = 0; i < 10; ++i) {
        final Map<String, BigInteger> map = wienerGenerator.generateAll(iterations, range);
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
  @Fork(value = 1)
  @Warmup(iterations = 0)
  @Measurement(iterations = 1, batchSize = 1)
  public void measureWiener(ExecutionPlan plan) {
    final Map<String, BigInteger> map = plan.wienerGenerator.generateAll(4096);
    final BigInteger N = map.get("N");
    final BigInteger e = map.get("e");
    System.out.println("N : " + N);
    System.out.println("e : " + e);
    System.out.println("d : " + map.get("d"));
    final BigInteger attack = new Dujel().attack(N, e, 100);
    System.out.println("Success : " + map.get("d").equals(attack));
    final BigInteger bigInteger = BigInteger.valueOf(2).modPow(e, N);
    final BigInteger bigInteger1 = bigInteger.modPow(attack, N);
    System.out.println("Attack : " + attack);
    System.out.println("Cypher : " + bigInteger);
    System.out.println("Encrypt : " + bigInteger1);
    System.out.println("Success : " + BigInteger.valueOf(2).equals(bigInteger1));

    //final BigInteger attack = new Wiener().attack(map.get("N"), map.get("e"), "2154215");
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  @Fork(value = 1, jvmArgs = {"-Xmx4g", "-Xms2g"})
  @Warmup(iterations = 0)
  @Measurement(iterations = 1)
  public void measureIterWiener(ExecutionPlan plan) {
    final Map<String, BigInteger> map = plan.list.get(ExecutionPlan.number);
    final BigInteger N = map.get("N");
    final BigInteger e = map.get("e");
    System.out.println("N : " + N);
    System.out.println("e : " + e);
    System.out.println("d : " + map.get("d"));
    final BigInteger attack = new Dujel().attack(N, e, plan.range);
    System.out.println("Success : " + map.get("d").equals(attack));
    final BigInteger bigInteger = BigInteger.valueOf(2).modPow(e, N);
    final BigInteger bigInteger1 = bigInteger.modPow(attack, N);
    System.out.println("Attack : " + attack);
    System.out.println("Cypher : " + bigInteger);
    System.out.println("Encrypt : " + bigInteger1);
    final boolean equals = BigInteger.valueOf(2).equals(bigInteger1);
    System.out.println("Success : " + equals);
    if (!equals) {
      ExecutionPlan.errors++;
    }
    if (ExecutionPlan.number >= 9) {
      ExecutionPlan.number = 0;
    } else {
      ExecutionPlan.number++;
    }

    //ExecutionPlan.number = 0;
    ExecutionPlan.all++;
    System.out.println("N length : " + N.bitLength());
    System.out.println("dLength : " + map.get("dLength"));
    System.out.println("number : " + ExecutionPlan.number);
    System.out.println("errors : " + ExecutionPlan.errors);
    System.out.println("all: " + ExecutionPlan.all);
    final double percent = (double) ExecutionPlan.errors / ExecutionPlan.all;
    System.out.println("error percent : " + percent);
    System.out.println("----------------------");
  }

}
