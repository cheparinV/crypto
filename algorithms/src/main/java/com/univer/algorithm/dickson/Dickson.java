package com.univer.algorithm.dickson;

import com.univer.algorithm.ferma.EratoshenesSieve;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

  public List<Integer> getBMap(Integer b, List<Integer> fBase) {
    final ArrayList<Integer> list = new ArrayList<>();
    for (Integer integer : fBase) {
      int k = 0;
      while (Math.floorMod(b, integer) == 0) {
        b /= integer;
        k++;
      }

      list.add(
          Math.floorMod(k, 2)
      );
    }
    if (b != 1) {
      return new ArrayList<>();
    }
    return list;
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

  public List<List<Integer>> gauss(List<List<Integer>> matrix) {
    final ArrayList<Integer> order = new ArrayList<>();
    final ArrayList<List<Integer>> unitMatrix = new ArrayList<>();
    for (int i = 0; i < matrix.size(); ++i) {
      order.add(i);
      final ArrayList<Integer> row = new ArrayList<>();
      for (int j = 0; j < matrix.size(); ++j) {
        if (i == j) {
          row.add(1);
        } else {
          row.add(0);
        }
        unitMatrix.add(row);
      }
    }
    final int m = matrix.size();
    final int n = matrix.get(0).size();
    int k = 0;
    while (k < n) {
      int j = k;
      while (j < m &&
          matrix.get(
              order.get(j)
          ).get(k) == 0) {
        j++;
      }
      if (j >= m) {
        k++;
        continue;
      }
      if (j > k) {
        Integer c = order.get(j);
        order.add(j, order.get(k));
        order.add(k, c);
      }

      for (int i = 0; i < m; ++i) {
        if (matrix.get(
            order.get(i)
        ).get(k) == 1) {
          for (int l = 0; l < n; ++l) {
            final int mod = Math.floorMod(
                matrix.get(i).get(l) + matrix.get(order.get(k)).get(l),
                2);
            matrix.get(order.get(i))
                .add(l, mod);
          }
          for (int l = 0; l < m; ++l) {
            final int mod = Math.floorMod(
                unitMatrix.get(i).get(l) + unitMatrix.get(order.get(k)).get(l),
                2);
            matrix.get(order.get(i))
                .add(l, mod);
          }
        }
      }
      k++;
    }
    final ArrayList<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < m; ++i) {
      if (!matrix.get(i).contains(1)) {
        result.add(unitMatrix.get(i));
      }
    }
    return result;


//    final List<Object[]> collect = matrix.stream()
//        .map(List::toArray)
//        .collect(Collectors.toList());
//    double[][] doubles = (double[][]) matrix.stream().map(
//        vector ->
//            vector.stream()
//                .mapToDouble(i -> i)
//                .toArray()
//    ).toArray();
//
//    RealMatrix coefficients =
//        new Array2DRowRealMatrix(doubles,
//            false);
//    double[] vector = new double[doubles.length];
//    Arrays.fill(vector, 0);
//    final DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
//    RealVector constants = new ArrayRealVector(vector, false);
//    RealVector solution = solver.solve(constants);
//    final List<Integer> list = new ArrayList<>();
//    for (int i = 0; i < solution.getDimension(); ++i) {
//      list.add((int) Math.round(solution.getEntry(i)));
//    }
//    return list;
  }


  public Long symbolLegendre(Integer a, Integer p) {
    final long exp = (p - 1) / 2;
    return BigInteger.valueOf(a)
        .modPow(
            BigInteger.valueOf(exp), BigInteger.valueOf(p)
        ).longValue();
  }
}
