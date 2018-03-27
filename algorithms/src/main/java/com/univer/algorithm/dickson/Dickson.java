package com.univer.algorithm.dickson;

import com.univer.algorithm.ferma.EratoshenesSieve;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Dickson {

  private Integer n;
  private Integer b;

  public Dickson(Integer n) {
    this.n = n;
  }

  public List<Integer> chooseFBase(int n, int b) {
    final List<Integer> list = new ArrayList<>();
    final List<Integer> primes = new EratoshenesSieve(b).getPrimes();
    for (Integer prime : primes) {
      if (this.symbolLegendre(n, prime) == 1) {
        list.add(prime);
      }
    }
    return list;
  }

  public Map<Integer, Integer> getBMap(Integer b, List<Integer> fBase) {
    Map<Integer, Integer> map = new HashMap<>();
    int quadro = b * b;
    for (Integer integer : fBase) {
      int k = 0;
      while (Math.floorMod(quadro, integer) == 0) {
        quadro /= integer;
        k++;
      }
      if (k != 0) {
        map.put(integer, k);
      }
    }
    if (quadro == 1) {
      return new HashMap<>();
    }
    return map;
  }

  public List<Integer> alphaArray(Map<Integer, Integer> map) {
    final ArrayList<Integer> list = new ArrayList<>();
    for (Integer integer : map.keySet()) {
      list.add(
          Math.floorMod(map.get(integer), 2)
      );
    }
    return list;
  }

  public List<Integer> gauss(List<List<Integer>> matrix) {
    final List<Object[]> collect = matrix.stream()
        .map(List::toArray)
        .collect(Collectors.toList());
    double[][] doubles = (double[][]) matrix.stream().map(
        vector ->
            vector.stream()
                .mapToDouble(i -> i)
                .toArray()
    ).collect(Collectors.toList()).toArray();

    RealMatrix coefficients =
        new Array2DRowRealMatrix(doubles,
            false);
    double[] vector = new double[doubles.length];
    Arrays.fill(vector, 0);
    final DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
    RealVector constants = new ArrayRealVector(vector, false);
    RealVector solution = solver.solve(constants);
    final List<Integer> list = new ArrayList<>();
    for (int i = 0; i < solution.getDimension(); ++i) {
      list.add((int) Math.round(solution.getEntry(i)));
    }
    return list;
  }


  public Long symbolLegendre(Integer a, Integer p) {
    final long exp = (p - 1) / 2;
    return BigInteger.valueOf(a)
        .modPow(
            BigInteger.valueOf(exp), BigInteger.valueOf(p)
        ).longValue();
  }
}
