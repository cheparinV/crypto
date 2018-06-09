package com.univer.algorithm.dickson;

import com.univer.algorithm.QSieve;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.Pair;
import org.junit.Test;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class DicksonTest {

  /**
   * ${CLASS} can work.
   *
   * @throws Exception If fails
   */
  @Test
  public void testGauss() throws Exception {

    RealMatrix coefficients =
        new Array2DRowRealMatrix(new double[][]{
            {1, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 1},
            {1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1},},
            false);
    final DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
    RealVector constants = new ArrayRealVector(new double[]{0, 0, 0, 0, 0, 0}, false);
    RealVector solution = solver.solve(constants);
    for (int i = 0; i < 3; ++i) {
      System.out.println(Math.round(solution.getEntry(i)));
    }
    System.out.println("Size = " + solution.getDimension());
  }

  @Test
  public void testDickson() throws Exception {
    int size = 96;
    for (int i = 1; i < 10; ++i) {
      size += 4;
      final BigInteger p = BigInteger.probablePrime(size, new Random());
      final BigInteger q = BigInteger.probablePrime(size, new Random());
      BigInteger n = p.multiply(q);
      System.out.println(size);
      System.out.println(n.bitLength());
      System.out.println(p);
      System.out.println(q);
      System.out.println(n);
      System.out.println("---------------------------------------------");
    }
    Integer n = 50611;
    Integer range = 1000;
    final int sqrt = (int) Math.sqrt(n);

    final Dickson dickson = new Dickson(n);
    final List<Integer> fBase = dickson.chooseFBase(n, 200);
    final HashMap<Integer, Integer> map = new HashMap<>();
    final ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();
    final ArrayList<List<Integer>> matrix = new ArrayList<>();
    //final int i1 = new Random().nextInt(sqrt);
    for (int i = -range / 2; i < range; ++i) {
      final int b = sqrt + i;
      final int mod = b * b - n;
      final List<Integer> list = dickson.getBMap(mod, fBase);
      if (list.isEmpty()) {
        continue;
      }
      pairs.add(new Pair<>(b, mod));
      matrix.add(list);
    }
    final List<List<Integer>> gauss = dickson.gauss(matrix);
    final Integer integer = new QSieve().gcdForQSieve(pairs, gauss, n);
    System.out.println(integer);
  }
}

