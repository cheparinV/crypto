package com.univer.algorithm.ferma;

import java.util.List;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Ferma {

  private Integer n;
  private Integer k;
  private List<Integer> primes;

  public Ferma(Integer n, Integer k) {
    this.n = n;
    this.k = k;
  }

  private Integer checkByPrimes() {
    this.primes = new EratoshenesSieve(k).getPrimes();
    for (Integer prime : this.primes) {
      if (n % prime == 0) {
        return prime;
      }
    }
    return 1;
  }

  private boolean checkConditions(Integer i) {
    final double var = this.getVarByNum(i);
    for (int j = i; j < this.primes.size(); j++) {
      final Integer prime = this.primes.get(j);
      final double tmp = var % prime;
      if (tmp == 0 &&
          (var % (prime * prime) == 0)) {
        return false;
      }
      if (tmp != 0 &&
          tmp / prime == -1) {
        return false;
      }
    }
    return true;
  }

  public Integer isComposite() {
    final Integer integer = this.checkByPrimes();
    if (integer > 1) {
      return integer;
    }
    for (int i = 1; i <= this.k; ++i) {
      if (this.checkConditions(i)) {
        final double probableSquare = this.getVarByNum(i);
        if (this.isSquare(probableSquare)) {
          return this.getFactor(i);
        }
      }
    }
    return integer;
  }

  private double getVarByNum(Integer i) {
    final double x = (int) Math.sqrt(this.n) + 1 +  i;
    return x * x - this.n;
  }

  private boolean isSquare(double probableSquare) {
    final double sqrt = (int) Math.sqrt(probableSquare);
    final double var = sqrt * sqrt;
    return var == probableSquare;
  }

  private Integer getFactor(Integer i) {
    final double var = this.getVarByNum(i);
    final double x = Math.sqrt(var + this.n);
    final double y = Math.sqrt(var);
    return (int) (x + y);

  }


}
