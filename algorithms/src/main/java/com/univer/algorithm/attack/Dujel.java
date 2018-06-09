package com.univer.algorithm.attack;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javafx.util.Pair;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Dujel extends Wiener {


  public BigInteger attack(BigInteger N, BigInteger e, Integer range) {
    final List<Pair<BigInteger, BigInteger>> convergents = this.convergents(
        this.contFraction(N, e));

    //Collections.reverse(convergents);
    final Integer numberM = this.findNumberOfConvergents(convergents, N, e);
    final List<Pair<BigInteger, BigInteger>> pairs = convergents.subList(numberM, numberM + 4);

    final Map<BigInteger, Integer> mapOne = new ConcurrentHashMap<>();
    final HashMap<BigInteger, Integer> mapTwo = new HashMap<>();
    final HashMap<BigInteger, Integer> mapThree = new HashMap<>();

    final BigInteger TWO = BigInteger.valueOf(2);
    BigInteger bOne = TWO.modPow(e.multiply(pairs.get(0).getValue()), N).modInverse(N);
    BigInteger bTwo = TWO.modPow(e.multiply(pairs.get(1).getValue()), N);
    BigInteger bThree = TWO.modPow(e.multiply(pairs.get(2).getValue()), N).modInverse(N);

    BigInteger a = BigInteger.valueOf(2L).modPow(e, N);

//    pairs.parallelStream().forEach(pair -> {
//          for (int i = 0; i < range; ++i) {
//            final BigInteger value = pair.getValue().multiply(BigInteger.valueOf(i));
//            mapOne.put(a.modPow(value, N), i);
//          }
//        }
//    );

    for (int i = 0; i <= 4 * range; ++i) {
      for (int j = 0; j < pairs.size(); ++j) {
        final BigInteger value = pairs.get(j).getValue().multiply(BigInteger.valueOf(i));
        final BigInteger e1 = a.modPow(value, N);
        mapOne.put(e1, i);
//        setTwo.add(e1, );
//        setThree.add(e1);
      }
    }

    final boolean[] isReady = {false};
    final ArrayList<BigInteger> result = new ArrayList<>();
    final int[] array = {0, 1, 2, 3};
//    IntStream.of(array)
//        //.unordered()
//        .parallel()
//        .forEach(j -> {
//          final int j1 = j * range;
//          System.out.println(j);
//          for (int i = j1; i < j1 + range && !isReady[0]; ++i) {
//            final BigInteger s = BigInteger.valueOf(i);
//            final BigInteger aOne = TWO.multiply(bOne.modPow(BigInteger.valueOf(i), N)).mod(N);
//            final BigInteger aTwo = TWO.multiply(bTwo.modPow(BigInteger.valueOf(i), N)).mod(N);
//            final BigInteger aThree = TWO.multiply(bThree.modPow(BigInteger.valueOf(i), N)).mod(N);
//
//            if (mapOne.containsKey(aTwo)) {
//              final BigInteger r = BigInteger.valueOf(mapOne.get(aTwo));
//              final BigInteger qOne = pairs.get(1).getValue();
//              final BigInteger qTwo = pairs.get(2).getValue();
//
//              System.out.println("R = " + r.toString());
//              System.out.println("S = " + s.toString());
//
//              result.add(qTwo.multiply(r).subtract(
//                  qOne.multiply(s)
//              ));
//              isReady[0] = true;
////        return qTwo.multiply(r).subtract(
////            qOne.multiply(s)
////        );
//            }
//            if (mapOne.containsKey(aOne)) {
//              final BigInteger r = BigInteger.valueOf(mapOne.get(aOne));
//              final BigInteger qOne = pairs.get(0).getValue();
//              final BigInteger qTwo = pairs.get(1).getValue();
//              System.out.println("R = " + r.toString());
//              System.out.println("S = " + s.toString());
//
//              result.add(qTwo.multiply(r).add(
//                  qOne.multiply(s)
//              ));
//              isReady[0] = true;
////        return qTwo.multiply(r).add(
////            qOne.multiply(s)
////        );
//            }
//
//            if (mapOne.containsKey(aThree)) {
//              final BigInteger r = BigInteger.valueOf(mapOne.get(aThree));
//              final BigInteger qOne = pairs.get(2).getValue();
//              final BigInteger qTwo = pairs.get(3).getValue();
//
//              System.out.println("R = " + r.toString());
//              System.out.println("S = " + s.toString());
//              result.add(qTwo.multiply(r).add(
//                  qOne.multiply(s)
//              ));
//              isReady[0] = true;
////        return qTwo.multiply(r).add(
////            qOne.multiply(s)
////        );
//            }
//            if (isReady[0]) {
//              break;
//            }
//          }
//        }
//    );

    for (int i = 0; i < 4 * range && !isReady[0]; ++i) {
      final BigInteger s = BigInteger.valueOf(i);
//      final BigInteger aOne = TWO.multiply(bOne.pow(i)).mod(N);
//      final BigInteger aTwo = TWO.multiply(bTwo.pow(i)).mod(N);
//      final BigInteger aThree = TWO.multiply(bThree.pow(i)).mod(N);

      final BigInteger aOne = TWO.multiply(bOne.modPow(BigInteger.valueOf(i), N)).mod(N);
      final BigInteger aTwo = TWO.multiply(bTwo.modPow(BigInteger.valueOf(i), N)).mod(N);
      final BigInteger aThree = TWO.multiply(bThree.modPow(BigInteger.valueOf(i), N)).mod(N);

      if (mapOne.containsKey(aTwo)) {
        final BigInteger r = BigInteger.valueOf(mapOne.get(aTwo));
        final BigInteger qOne = pairs.get(1).getValue();
        final BigInteger qTwo = pairs.get(2).getValue();

        System.out.println("R = " + r.toString());
        System.out.println("S = " + s.toString());

        result.add(qTwo.multiply(r).subtract(
            qOne.multiply(s)
        ));
        isReady[0] = true;
//        return qTwo.multiply(r).subtract(
//            qOne.multiply(s) 91088271337269846906430910304911322787
//        );
      }
      if (mapOne.containsKey(aOne)) {
        final BigInteger r = BigInteger.valueOf(mapOne.get(aOne));
        final BigInteger qOne = pairs.get(0).getValue();
        final BigInteger qTwo = pairs.get(1).getValue();
        System.out.println("R = " + r.toString());
        System.out.println("S = " + s.toString());

        result.add(qTwo.multiply(r).add(
            qOne.multiply(s)
        ));
        isReady[0] = true;
//        return qTwo.multiply(r).add(
//            qOne.multiply(s)
//        );
      }

      if (mapOne.containsKey(aThree)) {
        final BigInteger r = BigInteger.valueOf(mapOne.get(aThree));
        final BigInteger qOne = pairs.get(2).getValue();
        final BigInteger qTwo = pairs.get(3).getValue();

        System.out.println("R = " + r.toString());
        System.out.println("S = " + s.toString());
        result.add(qTwo.multiply(r).add(
            qOne.multiply(s)
        ));
        isReady[0] = true;
//        return qTwo.multiply(r).add(
//            qOne.multiply(s)
//        );
      }
    }
    for (BigInteger bigInteger : result) {
      final BigInteger two = BigInteger.valueOf(2);
      final BigInteger bigInteger1 = two.modPow(e, N).modPow(bigInteger, N);
      if (two.compareTo(bigInteger1) == 0) {
        return bigInteger;
      }
    }

    return BigInteger.ONE;//36958215659
  }

  private Integer findNumberOfConvergents(List<Pair<BigInteger, BigInteger>> convergents,
      BigInteger N, BigInteger e) {

    Integer m = 0;
    //Collections.reverse(convergents);
    for (int i = 0; i < convergents.size(); i += 2) {
      final BigInteger nom = convergents.get(i).getKey();
      final BigInteger dem = convergents.get(i).getValue();
      if (nom.divide(dem).compareTo(BigInteger.ONE) >= 0) {
        continue;
      }

      final BigDecimal sqrt = BigDecimalMath
          .sqrt(new BigDecimal(N.toString()), new MathContext(100));

      final BigInteger sqrtN = sqrt.toBigInteger();

      final BigInteger var1 = nom.multiply(N).multiply(sqrtN);
      final BigInteger var2 = e.multiply(sqrtN).multiply(dem);
      final BigInteger right = var1.subtract(var2);

      final BigInteger left = dem.multiply(e)
          .multiply(BigInteger.valueOf(2122L))
          .divide(BigInteger.valueOf(1000L));

//      final BigDecimal eDecimal = new BigDecimal(e.toString());
//      final BigDecimal NDecimal = new BigDecimal(N.toString());
//      final BigDecimal sqrtDecimal = new BigDecimal(sqrtN.toString());
//      final BigDecimal divide = eDecimal.divide(sqrtDecimal, 30, BigDecimal.ROUND_HALF_UP);
//      final BigDecimal divide1 = divide
//          .divide(NDecimal, 30, BigDecimal.ROUND_HALF_UP);
//      final BigDecimal divide2 = eDecimal.divide(NDecimal, 30, BigDecimal.ROUND_HALF_UP);
//      final BigDecimal add1 = divide1
//          .multiply(BigDecimal.valueOf(2.122))
//          .add(divide2);
//      final BigDecimal divide3 = new BigDecimal(nom.toString())
//          .divide(new BigDecimal(dem.toString()), 30,
//              BigDecimal.ROUND_HALF_UP);

      if (right.compareTo(left) < 0) {
        final BigInteger subtract = right.subtract(left);
        return i - 2;
      }
    }
    return 1;
  }


}
