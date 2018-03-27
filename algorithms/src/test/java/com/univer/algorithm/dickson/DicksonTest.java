package com.univer.algorithm.dickson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
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
    Integer n = 89755;
    final int sqrt = (int) Math.sqrt(n);

    final Dickson dickson = new Dickson(n);
    final List<Integer> fBase = dickson.chooseFBase(n, sqrt);
    final ArrayList<List<Integer>> matrix = new ArrayList<>();
    for (int i = 0; i < sqrt / 2; ++i) {
      final int b = sqrt + new Random().nextInt(n - sqrt);
      final Map<Integer, Integer> bMap = dickson.getBMap(b, fBase);
      matrix.add(
          dickson.alphaArray(bMap)
      );
    }
    dickson.gauss(matrix);
  }
}

