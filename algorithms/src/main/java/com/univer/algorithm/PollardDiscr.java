package com.univer.algorithm;

import java.math.BigInteger;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class PollardDiscr {

  private long prime;
  private long alpha;
  private long beta;

  public PollardDiscr(long alpha, long beta, long prime) {
    this.prime = prime;
    this.alpha = alpha;
    this.beta = beta;
  }

  public Long getX() {
    Long n = this.prime - 1;

    Long a1 = 0L;
    Long a2 = 0L;

    Long b1 = 0L;
    Long b2 = 0L;

    Long x1 = 1L;
    Long x2 = 1L;

    if (this.alpha == this.beta) {
      return 1L;
    }

    Boolean isStart = true;
    while (!x1.equals(x2) || isStart) {
      isStart = false;

      if (x1 < this.prime / 3) {
        x1 = (this.beta * x1) % this.prime;
        a1 = a1;
        b1 = (b1 + 1) % n;
      } else {
        if (x1 >= (this.prime / 3) && x1 < (2 * this.prime / 3)) {
          x1 = (x1 * x1) % this.prime;
          a1 = (2 * a1) % n;
          b1 = (2 * b1) % n;
        } else {
          if (x1 >= (2 * this.prime / 3)) {
            x1 = (this.alpha * x1) % this.prime;
            a1 = (a1 + 1) % n;
            b1 = b1;
          }
        }
      }

      for (int i = 0; i < 2; ++i) {
        if (x2 < this.prime / 3) {
          x2 = (this.beta * x2) % this.prime;
          a2 = a2;
          b2 = (b2 + 1) % n;
        } else {
          if (x2 >= (this.prime / 3) && x2 < (2 * this.prime / 3)) {
            x2 = (x2 * x2) % this.prime;
            a2 = (2 * a2) % n;
            b2 = (2 * b2) % n;
          } else {
            if (x2 >= (2 * this.prime / 3)) {
              x2 = (this.alpha * x2) % this.prime;
              a2 = (a2 + 1) % n;
              b2 = b2;
            }
          }
        }

      }
    }

    Long u = (a1 - a2) % n;
    Long v = (b2 - b1) % n;

    if (v % n == 0) {
      System.err.println("Not found x!");
      return 0L;
    }

    final Euclid euclid = new Euclid().countEuclid(BigInteger.valueOf(v), BigInteger.valueOf(n));
    Long d = Long.valueOf(euclid.getNOD());
    double nu = Math.pow(v, -1) % n;
    double x = 0L;
    for (int i = 0; i != d + 1; ++i) {
      final double v1 = u * nu;
      x = ((v1 + i * n) / d) % n;
      double mod = (Math.pow(this.alpha, x) - this.beta) % this.prime;
      if (mod == 0) {
        return (long) x;
      }
    }
    System.err.println("Not found x!");
    return (long) x;
  }

  public Long premutiveX() {
    Long x = 0L;
    while (x != this.prime) {
      final double mod = (Math.pow(this.alpha, x) - this.beta) % this.prime;
      if (mod == 0) {
        return x;
      }
      x++;
    }
    return 0L;
  }

  //  private Long nextU(Long u, Long z) {
//    Long newU = 0L;
//    Long xPrime = this.prime - 1;
//    if (z < this.prime / 3) {
//      newU = (u + 1) % xPrime;
//    }
//    if (z > (this.prime / 3) && z < (2 * this.prime / 3)) {
//      newU = (2 * u) % xPrime;
//    }
//    if (z > (2 * this.prime / 3)) {
//      newU = u % xPrime;
//    }
//    return newU;
//  }
//
//  private Long nextV(Long v, Long z) {
//    Long newV = 0L;
//    Long xPrime = this.prime - 1;
//    if (z < this.prime / 3) {
//      newV = v % xPrime;
//    }
//    if (z > (this.prime / 3) && z < (2 * this.prime / 3)) {
//      newV = (2 * v) % xPrime;
//    }
//    if (z > (2 * this.prime / 3)) {
//      newV = (v + 1) % xPrime;
//    }
//    return newV;
//  }
//
//  private Long nextZ(Long z) {
//    Long newZ = 0L;
//
//  }

}
